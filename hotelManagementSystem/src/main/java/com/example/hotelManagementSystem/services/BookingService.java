package com.example.hotelManagementSystem.services;

import com.example.hotelManagementSystem.dtos.BookingDTO;
import com.example.hotelManagementSystem.entities.Booking;
import com.example.hotelManagementSystem.entities.Customer;
import com.example.hotelManagementSystem.entities.PaymentStatus;
import com.example.hotelManagementSystem.entities.Room;
import com.example.hotelManagementSystem.exceptions.BookingNotFoundException;
import com.example.hotelManagementSystem.exceptions.CustomerNotFoundException;
import com.example.hotelManagementSystem.exceptions.RoomNotFoundException;
import com.example.hotelManagementSystem.exceptions.RoomOccupiedException;
import com.example.hotelManagementSystem.repositories.BookingRepository;
import com.example.hotelManagementSystem.repositories.CustomerRepository;
import com.example.hotelManagementSystem.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ModelMapper modelMapper;
    private final CustomerRepository customerRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ModelMapper modelMapper, CustomerRepository customerRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
        this.customerRepository = customerRepository;
        this.roomRepository = roomRepository;
    }

    public BookingDTO addBooking(BookingDTO bookingDTO) {
        Optional<Customer> customerOptional = customerRepository.findById(bookingDTO.getCustomerId());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer with id " + bookingDTO.getCustomerId() + " is not found");
    }
        Optional<Room> roomOptional = roomRepository.findByRoomNo(bookingDTO.getRoomNo());
        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room number " +bookingDTO.getRoomNo()+ " is not found");
        }
        //check if the room has been booked by any other customer on the same date or whether the dates are clashing
        Room room = roomOptional.get();
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                room.getRoomNo(),
                bookingDTO.getCheckIn(),
                bookingDTO.getCheckOut()
        );

        if (!overlappingBookings.isEmpty()) {
            throw new RoomOccupiedException("The room is already booked for the selected dates");
        }

        Booking booking = new Booking();
        booking.setCustomer(customerOptional.get());
        booking.setRoom(room);
        booking.setCheckIn(bookingDTO.getCheckIn());
        booking.setCheckOut(bookingDTO.getCheckOut());
        room.setAvailability(false);

        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDTO.class);

    }

    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> modelMapper.map(booking, BookingDTO.class))
                .collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new BookingNotFoundException("Booking with id " + id + " is not found");
        }
        return modelMapper.map(bookingOptional.get(), BookingDTO.class);
    }

    @Transactional
    public BookingDTO updateBooking(Long id, BookingDTO bookingDTO) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new BookingNotFoundException("Booking with id " + id + " is not found");
        }
        // Get the existing booking
        Booking booking = bookingOptional.get();

        // Check if new check-in or check-out dates clash with other bookings
        LocalDateTime newCheckIn = bookingDTO.getCheckIn() != null ? bookingDTO.getCheckIn() : booking.getCheckIn();
        LocalDateTime newCheckOut = bookingDTO.getCheckOut() != null ? bookingDTO.getCheckOut() : booking.getCheckOut();

        // Find overlapping bookings with the updated dates
        List<Booking> overlappingBookings = bookingRepository.findOverlappingBookings(
                booking.getRoom().getRoomNo(),
                newCheckIn,
                newCheckOut
        );

        // Remove the current booking from the list of overlapping bookings, if present
        //overlappingBookings.removeIf(b -> b.getId().equals(booking.getId()));

        // If there are any other overlapping bookings, throw an exception
        if (!overlappingBookings.isEmpty()) {
            throw new RoomOccupiedException("The room is already booked for the selected dates");
        }

        // Update the check-in and check-out dates if provided
        if (bookingDTO.getCheckIn() != null) {
            booking.setCheckIn(bookingDTO.getCheckIn());
        }
        if (bookingDTO.getCheckOut() != null) {
            booking.setCheckOut(bookingDTO.getCheckOut());
        }

        booking.setPaymentStatus(PaymentStatus.PENDING);

        // Save the updated booking
        Booking updatedBooking = bookingRepository.save(booking);

        // Map the updated booking entity to BookingDTO and return
        return modelMapper.map(updatedBooking, BookingDTO.class);

    }

    public void deleteBooking(Long id) {
        Optional<Booking> bookingOptional = bookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new BookingNotFoundException("Booking with id " + id + " is not found");
        }
        bookingRepository.deleteById(id);
    }
}

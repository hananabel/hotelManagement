package com.example.hotelManagementSystem.repositories;

import com.example.hotelManagementSystem.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE b.room.roomNo = :roomId AND " +
            "(b.checkIn < :checkOut AND b.checkOut > :checkIn)")
    List<Booking> findOverlappingBookings(Integer roomId, LocalDateTime checkIn, LocalDateTime checkOut);
}


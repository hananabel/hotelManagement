package com.example.hotelManagementSystem.services;

import com.example.hotelManagementSystem.dtos.CustomerDTO;
import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.entities.Room;
import com.example.hotelManagementSystem.exceptions.RoomAlreadyExists;
import com.example.hotelManagementSystem.exceptions.RoomNotFoundException;
import com.example.hotelManagementSystem.repositories.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    public RoomDTO addRoom(Room room) {
        Optional<Room> roomNumber = roomRepository.findByRoomNo(room.getRoomNo());
        if (roomNumber.isPresent()) {
            throw new RoomAlreadyExists("Room number " + room.getRoomNo() + " already exists");
        }
        return modelMapper.map(roomRepository.save(room), RoomDTO.class);
    }

    public List<RoomDTO> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    public RoomDTO getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty()) {
            throw new RoomNotFoundException("Room with id " + id + " is not found");
        }
        return modelMapper.map(room.get(), RoomDTO.class);
    }

    public RoomDTO updateRoom(Long id, Room updatedRoom) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room with id " + id + " is not found");
        }
        Room room = roomOptional.get();
        if(updatedRoom.getRoomType() != null){
            room.setRoomType(updatedRoom.getRoomType());
        }
        if(updatedRoom.getCapacity() != null) {
            room.setCapacity(updatedRoom.getCapacity());
        }
        if(updatedRoom.getPrice() != null) {
            room.setPrice(updatedRoom.getPrice());
        }
        if(updatedRoom.getAvailability() != null) {
            room.setAvailability(updatedRoom.getAvailability());
        }
        return modelMapper.map(roomRepository.save(room), RoomDTO.class);
    }

    public void deleteRoomById(Long id) {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (roomOptional.isEmpty()) {
            throw new RoomNotFoundException("Room with id " + id + " is not found");
        }
        roomRepository.delete(roomOptional.get());
    }

}

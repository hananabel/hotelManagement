package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRoomsApi {

    private final RoomService roomService;

    @Autowired
    public GetRoomsApi(RoomService roomService) {
        this.roomService = roomService;
    }

    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return ResponseEntity.status(HttpStatus.OK).body(roomService.getAllRooms());
    }
}

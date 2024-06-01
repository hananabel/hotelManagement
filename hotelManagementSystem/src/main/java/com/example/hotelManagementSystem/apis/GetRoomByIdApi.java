package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GetRoomByIdApi {

    private final RoomService roomService;

    @Autowired
    public GetRoomByIdApi(RoomService roomService) {
        this.roomService = roomService;
    }

    public ResponseEntity<RoomDTO> getRoomById(Long id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
}

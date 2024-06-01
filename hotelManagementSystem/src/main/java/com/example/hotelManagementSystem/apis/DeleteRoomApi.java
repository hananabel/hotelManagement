package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DeleteRoomApi {

    private final RoomService roomService;

    @Autowired
    public DeleteRoomApi(RoomService roomService) {
        this.roomService = roomService;
    }

    public ResponseEntity<String> deleteRoom(Long id) {
        roomService.deleteRoomById(id);
        return ResponseEntity.ok().body("Deleted room with id " + id);
    }
}

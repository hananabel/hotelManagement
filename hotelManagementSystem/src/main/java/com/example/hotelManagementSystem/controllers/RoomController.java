package com.example.hotelManagementSystem.controllers;

import com.example.hotelManagementSystem.apis.*;
import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.entities.Room;
import com.example.hotelManagementSystem.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/room")
public class RoomController {

    private final AddRoomApi addRoomApi;
    private final GetRoomsApi getRoomsApi;
    private final GetRoomByIdApi getRoomByIdApi;
    private final UpdateRoomApi updateRoomApi;
    private final DeleteRoomApi deleteRoomApi;

    @Autowired
    public RoomController(AddRoomApi addRoomApi, GetRoomsApi getRoomsApi, GetRoomByIdApi getRoomByIdApi, UpdateRoomApi updateRoomApi, DeleteRoomApi deleteRoomApi) {
        this.addRoomApi = addRoomApi;
        this.getRoomsApi = getRoomsApi;
        this.getRoomByIdApi = getRoomByIdApi;
        this.updateRoomApi = updateRoomApi;
        this.deleteRoomApi = deleteRoomApi;
    }

    @PostMapping("/addRoom")
    public ResponseEntity<ApiResponse<RoomDTO>> addRoom(@RequestBody Room room) {
        return addRoomApi.addRoom(room);
    }

    @GetMapping("/getAllRooms")
    public ResponseEntity<List<RoomDTO>> getAllRooms() {
        return getRoomsApi.getAllRooms();
    }

    @GetMapping(path = "/getRoom/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return getRoomByIdApi.getRoomById(id);
    }

    @PutMapping("/updateRoom/{id}")
    public ResponseEntity<ApiResponse<RoomDTO>> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return updateRoomApi.updateRoom(id, room);
    }

    @DeleteMapping("deleteRoom/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable Long id) {
        return deleteRoomApi.deleteRoom(id);
    }
}

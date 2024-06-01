package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.entities.Room;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.RoomService;
import com.example.hotelManagementSystem.validations.RoomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddRoomApi {

    private final RoomService roomService;
    private final RoomValidationUtils validate;

    @Autowired
    public AddRoomApi(RoomService roomService, RoomValidationUtils validate) {
        this.roomService = roomService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<RoomDTO>> addRoom(Room room) {
        try {
            validate.validateRoom(room);
            RoomDTO roomDTO = roomService.addRoom(room);
            ApiResponse<RoomDTO> response = new ApiResponse<>("Room added successfully", roomDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch(IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}

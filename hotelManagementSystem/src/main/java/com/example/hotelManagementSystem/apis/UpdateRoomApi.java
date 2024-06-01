package com.example.hotelManagementSystem.apis;

import com.example.hotelManagementSystem.dtos.RoomDTO;
import com.example.hotelManagementSystem.entities.Room;
import com.example.hotelManagementSystem.response.ApiResponse;
import com.example.hotelManagementSystem.services.RoomService;
import com.example.hotelManagementSystem.validations.RoomValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UpdateRoomApi {

    private final RoomService roomService;
    private final RoomValidationUtils validate;

    @Autowired
    public UpdateRoomApi(RoomService roomService, RoomValidationUtils validate) {
        this.roomService = roomService;
        this.validate = validate;
    }

    public ResponseEntity<ApiResponse<RoomDTO>> updateRoom(Long id, Room room) {
        try {
            validate.validateUpdatedRoom(room);
            RoomDTO roomDTO = roomService.updateRoom(id, room);
            ApiResponse<RoomDTO> response = new ApiResponse<>("Room Updated Successfully", roomDTO);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException ex) {
            // Return bad request with error message
            return ResponseEntity.badRequest().body(new ApiResponse<>(ex.getMessage(), null));
        }
    }
}

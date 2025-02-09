package com.alertify.controllers;

import com.alertify.dto.UserDTO;
import com.alertify.service.UserService;
import com.alertify.util.ApiSuccessResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;


@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<UserDTO>> createUser(@RequestBody @Valid @NotNull UserDTO userDTO) {
        log.info("Creating user with email: {}", userDTO.getEmail());
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiSuccessResponse.create(createdUser, "User created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<UserDTO>> getUserById(@PathVariable @NotNull Long id) {
        log.info("Fetching user with ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiSuccessResponse.create(user, "User retrieved successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiSuccessResponse<List<UserDTO>>> getAllUsers() {
        log.info("Fetching all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiSuccessResponse.create(users, "All Users retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<UserDTO>> updateUser(
            @PathVariable @NotNull Long id, @RequestBody @Valid UserDTO userDTO) {
        log.info("Updating user with ID: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(ApiSuccessResponse.create(updatedUser, "User updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<String>> deleteUser(@PathVariable @NotNull Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiSuccessResponse.create("User deleted successfully", "Success"));
    }
}

package com.alertify.controllers;

import com.alertify.dto.UserDTO;
import com.alertify.dto.UserWithTasksDTO;
import com.alertify.service.UserService;
import com.alertify.util.ApiSuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@Validated
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "User Controller", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Create a new user", description = "Creates a new user with the provided details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user details provided")
    })
    @PostMapping
    public ResponseEntity<ApiSuccessResponse<UserDTO>> createUser(@RequestBody @Valid @NotNull UserDTO userDTO) {
        log.info("Creating user with email: {}", userDTO.getEmail());
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiSuccessResponse.create(createdUser, "User created successfully"));
    }

    @Operation(summary = "Get a user by ID", description = "Fetches user details by user ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<UserDTO>> getUserById(@PathVariable @NotNull Long id) {
        log.info("Fetching user with ID: {}", id);
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(ApiSuccessResponse.create(user, "User retrieved successfully"));
    }

    @Operation(summary = "Get all users with their tasks", description = "Fetches a list of all users along with their assigned tasks.")
    @GetMapping("/with-tasks")
    public ResponseEntity<ApiSuccessResponse<List<UserWithTasksDTO>>> getUsersWithTasks() {
        log.info("Fetching all users with their tasks");
        List<UserWithTasksDTO> usersWithTasks = userService.getUsersWithTasks();
        return ResponseEntity.ok(ApiSuccessResponse.create(usersWithTasks, "All users with tasks retrieved successfully"));
    }

    @Operation(summary = "Get all users", description = "Fetches a list of all users.")
    @GetMapping
    public ResponseEntity<ApiSuccessResponse<List<UserDTO>>> getAllUsers() {
        log.info("Fetching all users");
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(ApiSuccessResponse.create(users, "All users retrieved successfully"));
    }

    @Operation(summary = "Update a user", description = "Updates user details by user ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<UserDTO>> updateUser(
            @PathVariable @NotNull Long id, @RequestBody @Valid UserDTO userDTO) {
        log.info("Updating user with ID: {}", id);
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(ApiSuccessResponse.create(updatedUser, "User updated successfully"));
    }

    @Operation(summary = "Delete a user", description = "Deletes a user by ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<String>> deleteUser(@PathVariable @NotNull Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.ok(ApiSuccessResponse.create("User deleted successfully", "Success"));
    }
}

package com.alertify.controllers;

import com.alertify.dto.TaskDTO;
import com.alertify.service.TaskService;
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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Task Controller", description = "APIs for managing tasks")
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Create a new task", description = "Creates a new task with the given details.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid task details provided")
    })
    @PostMapping
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> createTask(@RequestBody @Valid @NotNull TaskDTO taskDTO) {
        log.info("Creating task with title: {}", taskDTO.getTitle());
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiSuccessResponse.create(createdTask, "Task created successfully"));
    }

    @Operation(summary = "Get a task by ID", description = "Fetches a task by its ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task found"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> getTaskById(@PathVariable @NotNull Long id) {
        log.info("Fetching task with ID: {}", id);
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(ApiSuccessResponse.create(task, "Task retrieved successfully"));
    }

    @Operation(summary = "Get all tasks", description = "Fetches a list of all tasks.")
    @GetMapping
    public ResponseEntity<ApiSuccessResponse<List<TaskDTO>>> getAllTasks() {
        log.info("Fetching all tasks");
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ApiSuccessResponse.create(tasks, "Tasks retrieved successfully"));
    }

    @Operation(summary = "assign tasks to a user", description = "assign tasks to a user.")
    @PutMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        log.info("Assigning task {} to user {}", taskId, userId);
        TaskDTO updatedTask = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(ApiSuccessResponse.create(updatedTask, "Task assigned successfully"));
    }

    @Operation(summary = "Update a task", description = "Updates task details by task ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> updateTask(
            @PathVariable @NotNull Long id, @RequestBody @Valid TaskDTO taskDTO) {
        log.info("Updating task with ID: {}", id);
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(ApiSuccessResponse.create(updatedTask, "Task updated successfully"));
    }

    @Operation(summary = "Delete a task", description = "Deletes a task by its ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<String>> deleteTask(@PathVariable @NotNull Long id) {
        log.info("Deleting task with ID: {}", id);
        taskService.deleteTask(id);
        return ResponseEntity.ok(ApiSuccessResponse.create("Task deleted successfully", "Success"));
    }
}

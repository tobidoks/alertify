package com.alertify.controllers;

import com.alertify.dto.TaskDTO;
import com.alertify.service.TaskService;
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
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> createTask(@RequestBody @Valid @NotNull TaskDTO taskDTO) {
        log.info("Creating task with title: {}", taskDTO.getTitle());
        TaskDTO createdTask = taskService.createTask(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiSuccessResponse.create(createdTask, "Task created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> getTaskById(@PathVariable @NotNull Long id) {
        log.info("Fetching task with ID: {}", id);
        TaskDTO task = taskService.getTaskById(id);
        return ResponseEntity.ok(ApiSuccessResponse.create(task, "Task retrieved successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiSuccessResponse<List<TaskDTO>>> getAllTasks() {
        log.info("Fetching all tasks");
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(ApiSuccessResponse.create(tasks, "Tasks retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<TaskDTO>> updateTask(
            @PathVariable @NotNull Long id, @RequestBody @Valid TaskDTO taskDTO) {
        log.info("Updating task with ID: {}", id);
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(ApiSuccessResponse.create(updatedTask, "Task updated successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiSuccessResponse<String>> deleteTask(@PathVariable @NotNull Long id) {
        log.info("Deleting task with ID: {}", id);
        taskService.deleteTask(id);
        return ResponseEntity.ok(ApiSuccessResponse.create("Task deleted successfully", "Success"));
    }
}

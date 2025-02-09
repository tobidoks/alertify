package com.alertify.service;

import com.alertify.dto.TaskDTO;
import com.alertify.exceptions.ResourceNotFoundException;
import com.alertify.model.Task;
import com.alertify.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),
                        task.getPriority(), task.getStatus(), task.getDueDate()))
                .collect(Collectors.toList());
    }

    public TaskDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),
                task.getPriority(), task.getStatus(), task.getDueDate());
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = new Task(null, taskDTO.getTitle(), taskDTO.getDescription(),
                taskDTO.getPriority(), taskDTO.getStatus(), taskDTO.getDueDate(), null);
        Task savedTask = taskRepository.save(task);

        return new TaskDTO(savedTask.getId(), savedTask.getTitle(), savedTask.getDescription(),
                savedTask.getPriority(), savedTask.getStatus(), savedTask.getDueDate());
    }

    @Transactional
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus());
        task.setDueDate(taskDTO.getDueDate());

        Task updatedTask = taskRepository.save(task);
        return new TaskDTO(updatedTask.getId(), updatedTask.getTitle(), updatedTask.getDescription(),
                updatedTask.getPriority(), updatedTask.getStatus(), updatedTask.getDueDate());
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }
}

package com.alertify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class UserWithTasksDTO {
    private Long id;
    private String username;
    private String email;
    private List<TaskDTO> tasks;
}


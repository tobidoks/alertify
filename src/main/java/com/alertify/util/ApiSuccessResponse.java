package com.alertify.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "create")
public class ApiSuccessResponse<T> {
    private T data;
    private String message;
}

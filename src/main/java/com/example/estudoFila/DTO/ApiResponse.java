package com.example.estudoFila.DTO;

import java.time.LocalDateTime;

public record ApiResponse<T>(

        int status,

        String message,

        T data,

        LocalDateTime timestamp

) {

    public static <T> ApiResponse<T> success(int status, String message, T data) {

        return new ApiResponse<>(status, message, data, LocalDateTime.now());

    }

    public static <T> ApiResponse<T> error(int status, String message) {

        return new ApiResponse<>(status, message, (T) null, LocalDateTime.now());

    }

}

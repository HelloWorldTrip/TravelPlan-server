package com.HelloWorldTrip.TravelPlan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    @Getter
    private boolean success;
    private String message;
    private T data;
    private HttpStatus status;
}
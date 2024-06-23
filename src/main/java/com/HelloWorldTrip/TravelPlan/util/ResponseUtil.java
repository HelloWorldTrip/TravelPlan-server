package com.HelloWorldTrip.TravelPlan.util;

import com.HelloWorldTrip.TravelPlan.dto.ApiResponse;
import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ApiResponse<T> buildResponse(ApiMessage message, T data) {
        return new ApiResponse<>(message.getStatus().is2xxSuccessful(), message.getMessage(), data, message.getStatus());
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(ApiMessage message, T data) {
        return new ResponseEntity<>(buildResponse(message, data), message.getStatus());
    }
}

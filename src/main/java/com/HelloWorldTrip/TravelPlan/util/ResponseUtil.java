package com.HelloWorldTrip.TravelPlan.util;

import com.HelloWorldTrip.TravelPlan.dto.ApiResponse;
import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static <T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(T data, ApiMessage message) {
        ApiResponse<T> apiResponse = new ApiResponse<>(true, message.getMessage(), data);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse<Void>> buildSuccessResponse(ApiMessage message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>(true, message.getMessage(), null);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(ApiMessage errorMessage, HttpStatus status) {
        ApiResponse<T> apiResponse = new ApiResponse<>(false, errorMessage.getMessage(), null);
        return new ResponseEntity<>(apiResponse, status);
    }
}

package com.HelloWorldTrip.TravelPlan.dto;

import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;

    public LoginResponse(ApiMessage apiMessage) {
        this.success = apiMessage.getStatus().is2xxSuccessful();
        this.message = apiMessage.getMessage();
    }
}

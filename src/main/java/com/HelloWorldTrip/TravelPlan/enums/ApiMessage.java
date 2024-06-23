package com.HelloWorldTrip.TravelPlan.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiMessage {
    // Success Messages
    MEMBERS_RETRIEVED_SUCCESS("Members retrieved successfully", HttpStatus.OK),
    MEMBER_RETRIEVED_SUCCESS("Member retrieved successfully", HttpStatus.OK),
    MEMBER_REGISTERED_SUCCESS("Member registered successfully", HttpStatus.CREATED),
    MEMBER_UPDATED_SUCCESS("Member updated successfully", HttpStatus.OK),
    PASSWORD_CHANGED_SUCCESS("Password changed successfully", HttpStatus.OK),
    MEMBER_DEACTIVATED_SUCCESS("Member deactivated successfully", HttpStatus.OK),

    // Error Messages
    MEMBER_NOT_FOUND("Member not found", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_RETRIEVE_MEMBERS("Failed to retrieve members", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_REGISTER_MEMBER("Failed to register member", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_CHANGE_PASSWORD("Failed to change password", HttpStatus.INTERNAL_SERVER_ERROR),
    FAILED_TO_DEACTIVATE_MEMBER("Failed to deactivate member", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED("Validation failed", HttpStatus.BAD_REQUEST),
    VALIDATION_PW_FAILED("Validation password failed", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus status;

    ApiMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}



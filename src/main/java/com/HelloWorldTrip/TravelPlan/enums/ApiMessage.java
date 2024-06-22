package com.HelloWorldTrip.TravelPlan.enums;

import lombok.Getter;

@Getter
public enum ApiMessage {
    // Success Messages
    MEMBERS_RETRIEVED_SUCCESS("SUCCESS_001", "Members retrieved successfully"),
    MEMBER_RETRIEVED_SUCCESS("SUCCESS_002", "Member retrieved successfully"),
    MEMBER_REGISTERED_SUCCESS("SUCCESS_003", "Member registered successfully"),
    PASSWORD_CHANGED_SUCCESS("SUCCESS_004", "Password changed successfully"),
    MEMBER_DEACTIVATED_SUCCESS("SUCCESS_005", "Member deactivated successfully"),

    // Error Messages
    MEMBER_NOT_FOUND("ERROR_001", "Member not found"),
    USER_NOT_FOUND("ERROR_002", "User not found"),
    INTERNAL_SERVER_ERROR("ERROR_003", "An internal server error occurred"),
    FAILED_TO_RETRIEVE_MEMBERS("ERROR_004", "Failed to retrieve members"),
    FAILED_TO_REGISTER_MEMBER("ERROR_005", "Failed to register member"),
    FAILED_TO_CHANGE_PASSWORD("ERROR_006", "Failed to change password"),
    FAILED_TO_DEACTIVATE_MEMBER("ERROR_007", "Failed to deactivate member");

    private final String code;
    private final String message;

    ApiMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }
}


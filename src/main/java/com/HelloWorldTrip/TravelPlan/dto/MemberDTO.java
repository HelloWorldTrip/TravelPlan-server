package com.HelloWorldTrip.TravelPlan.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MemberDTO {
    @NotNull(message = "User ID cannot be null")
    private String userId;
    @NotNull(message = "User Name cannot be null")
    private String userName;
    @NotNull(message = "User Password cannot be null")
    private String userPw;
    private String useYn;
}

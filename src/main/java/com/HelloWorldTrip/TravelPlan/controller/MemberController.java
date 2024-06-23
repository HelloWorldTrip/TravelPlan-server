package com.HelloWorldTrip.TravelPlan.controller;

import com.HelloWorldTrip.TravelPlan.dto.ApiResponse;
import com.HelloWorldTrip.TravelPlan.dto.MemberDTO;
import com.HelloWorldTrip.TravelPlan.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/members")
@Tag(name = "Member API", description = "The Member API")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    @Operation(summary = "Get members", description = "This is Get all member list")
    public ResponseEntity<ApiResponse<List<MemberDTO>>> getAllMembers() {
        ApiResponse<List<MemberDTO>> response = memberService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get member", description = "This is Get member by userId")
    public ResponseEntity<ApiResponse<MemberDTO>> getMemberById(@PathVariable @Valid String userId) {
        ApiResponse<MemberDTO> response = memberService.findByUserId(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping
    @Operation(summary = "Register new member", description = "This is Register a new member")
    public ResponseEntity<?> registerMember(@RequestBody @Valid MemberDTO memberDTO){
        ApiResponse<MemberDTO> response = memberService.register(memberDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update member", description = "Update an existing member's details")
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<MemberDTO>> updateMember(@PathVariable @Valid String userId, @RequestBody @Valid MemberDTO memberDTO) {
        ApiResponse<MemberDTO> response = memberService.update(userId, memberDTO);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Change password", description = "Change the password of an existing member")
    @PatchMapping("/{userId}/password")
    public ResponseEntity<ApiResponse<MemberDTO>> updatePassword(@PathVariable @Valid String userId, @RequestBody @NotNull String newPassword) {
        ApiResponse<MemberDTO> response = memberService.updatePassword(userId, newPassword);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Deactivate member", description = "Deactivate a member by ID")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deactivateMember(@PathVariable @Valid String userId) {
        ApiResponse<Void> response = memberService.deactivateById(userId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}

package com.HelloWorldTrip.TravelPlan.service;

import com.HelloWorldTrip.TravelPlan.dto.ApiResponse;
import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import com.HelloWorldTrip.TravelPlan.mapper.MemberMapper;
import com.HelloWorldTrip.TravelPlan.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.HelloWorldTrip.TravelPlan.repository.MemberEntityRepository;
import com.HelloWorldTrip.TravelPlan.domain.MemberEntity;
import com.HelloWorldTrip.TravelPlan.dto.MemberDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberEntityRepository memberRepository;
    private final MemberMapper memberMapper = MemberMapper.INSTANCE;

    private BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberEntityRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public ResponseEntity<ApiResponse<List<MemberDTO>>> findAll() {
        try {
            List<MemberDTO> members = memberRepository.findAll().stream()
                    .map(memberMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseUtil.buildSuccessResponse(members, ApiMessage.MEMBERS_RETRIEVED_SUCCESS);
        } catch (Exception e){
            return ResponseUtil.buildErrorResponse(ApiMessage.FAILED_TO_RETRIEVE_MEMBERS, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApiResponse<MemberDTO>> findById(Long id) {
        try {
            Optional<MemberDTO> member = memberRepository.findById(id)
                    .map(memberMapper::toDTO);
            if (member.isPresent()) {
                return ResponseUtil.buildSuccessResponse(member.get(), ApiMessage.MEMBER_RETRIEVED_SUCCESS);
            } else {
                return ResponseUtil.buildErrorResponse(ApiMessage.MEMBER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return ResponseUtil.buildErrorResponse(ApiMessage.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<ApiResponse<MemberDTO>> register(MemberDTO memberDTO) {
        try {
            MemberEntity member = memberMapper.toEntity(memberDTO);
            member.setUserPw(passwordEncoder.encode(memberDTO.getUserPw())); // 비밀번호 암호화
            member.setFrstDate(LocalDateTime.now());
            member.setLastDate(LocalDateTime.now());
            member.setUseYn("Y");
            MemberEntity savedMember = memberRepository.save(member);
            MemberDTO responseDTO = memberMapper.toDTO(savedMember);
            return ResponseUtil.buildSuccessResponse(responseDTO, ApiMessage.MEMBER_REGISTERED_SUCCESS);
        } catch (Exception e){
            return ResponseUtil.buildErrorResponse(ApiMessage.FAILED_TO_REGISTER_MEMBER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApiResponse<MemberDTO>> updatePassword(String userId, String newPassword) {
        Optional<MemberEntity> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            member.setUserPw(passwordEncoder.encode(newPassword));
            member.setLastDate(LocalDateTime.now());
            MemberEntity updatedMember = memberRepository.save(member);
            MemberDTO responseDTO = memberMapper.toDTO(updatedMember);
            return ResponseUtil.buildSuccessResponse(responseDTO, ApiMessage.PASSWORD_CHANGED_SUCCESS);
        } else {
            return ResponseUtil.buildErrorResponse(ApiMessage.FAILED_TO_CHANGE_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ApiResponse<Void>> deactivateById(String userId) {
        try {
            Optional<MemberEntity> optionalMember = memberRepository.findByUserId(userId);
            if (optionalMember.isPresent()) {
                MemberEntity member = optionalMember.get();
                member.setUseYn("N");
                memberRepository.save(member);
                return ResponseUtil.buildSuccessResponse(ApiMessage.MEMBER_DEACTIVATED_SUCCESS);
            } else {
                return ResponseUtil.buildErrorResponse(ApiMessage.MEMBER_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e){
            return ResponseUtil.buildErrorResponse(ApiMessage.FAILED_TO_DEACTIVATE_MEMBER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.HelloWorldTrip.TravelPlan.service;

import com.HelloWorldTrip.TravelPlan.dto.ApiResponse;
import com.HelloWorldTrip.TravelPlan.enums.ApiMessage;
import com.HelloWorldTrip.TravelPlan.mapper.MemberMapper;
import com.HelloWorldTrip.TravelPlan.util.ResponseUtil;
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

    public ApiResponse<List<MemberDTO>> findAll() {
        try {
            List<MemberDTO> members = memberRepository.findAll().stream()
                    .map(memberMapper::toDTO)
                    .collect(Collectors.toList());
            return ResponseUtil.buildResponse(ApiMessage.MEMBERS_RETRIEVED_SUCCESS, members);
        } catch (Exception e){
            return ResponseUtil.buildResponse(ApiMessage.FAILED_TO_RETRIEVE_MEMBERS, null);
        }
    }

    public ApiResponse<MemberDTO> findByUserId(String userId) {
        try {
            Optional<MemberDTO> member = memberRepository.findByUserId(userId)
                    .map(memberMapper::toDTO);
            if (member.isPresent()) {
                return ResponseUtil.buildResponse(ApiMessage.MEMBER_RETRIEVED_SUCCESS, member.get());
            } else {
                return ResponseUtil.buildResponse(ApiMessage.MEMBER_NOT_FOUND, null);
            }
        } catch (Exception e){
            return ResponseUtil.buildResponse(ApiMessage.INTERNAL_SERVER_ERROR, null);
        }

    }

    public ApiResponse<MemberDTO> register(MemberDTO memberDTO) {
        try {
            MemberEntity member = memberMapper.toEntity(memberDTO);
            member.setUserPw(passwordEncoder.encode(memberDTO.getUserPw())); // 비밀번호 암호화
            member.setFrstDate(LocalDateTime.now());
            member.setLastDate(LocalDateTime.now());
            member.setUseYn("Y");
            MemberEntity savedMember = memberRepository.save(member);
            MemberDTO responseDTO = memberMapper.toDTO(savedMember);
            return ResponseUtil.buildResponse(ApiMessage.MEMBER_REGISTERED_SUCCESS, responseDTO);
        } catch (Exception e){
            return ResponseUtil.buildResponse(ApiMessage.FAILED_TO_REGISTER_MEMBER, null);
        }
    }

    public ApiResponse<MemberDTO> update(String userId, MemberDTO memberDTO) {
        try {
            Optional<MemberEntity> optionalMember = memberRepository.findByUserId(userId);
            if (optionalMember.isPresent()) {
                MemberEntity member = optionalMember.get();
                if (passwordEncoder.matches(memberDTO.getUserPw(), member.getUserPw())) {
                    member.setUserName(memberDTO.getUserName());
                    member.setLastDate(LocalDateTime.now());
                    MemberEntity updatedMember = memberRepository.save(member);
                    MemberDTO responseDTO = memberMapper.toDTO(updatedMember);
                    return ResponseUtil.buildResponse(ApiMessage.MEMBER_UPDATED_SUCCESS, responseDTO);
                } else {
                    return ResponseUtil.buildResponse(ApiMessage.VALIDATION_PW_FAILED, null);
                }
            } else {
                return ResponseUtil.buildResponse(ApiMessage.MEMBER_NOT_FOUND, null);
            }
        } catch (Exception e) {
            return ResponseUtil.buildResponse(ApiMessage.INTERNAL_SERVER_ERROR, null);
        }
    }

    public ApiResponse<MemberDTO> updatePassword(String userId, String newPassword) {
        Optional<MemberEntity> optionalMember = memberRepository.findByUserId(userId);
        if (optionalMember.isPresent()) {
            MemberEntity member = optionalMember.get();
            member.setUserPw(passwordEncoder.encode(newPassword));
            member.setLastDate(LocalDateTime.now());
            MemberEntity updatedMember = memberRepository.save(member);
            MemberDTO responseDTO = memberMapper.toDTO(updatedMember);
            return ResponseUtil.buildResponse(ApiMessage.PASSWORD_CHANGED_SUCCESS, responseDTO);
        } else {
            return ResponseUtil.buildResponse(ApiMessage.FAILED_TO_CHANGE_PASSWORD, null);
        }
    }

    public ApiResponse<Void> deactivateById(String userId) {
        try {
            Optional<MemberEntity> optionalMember = memberRepository.findByUserId(userId);
            if (optionalMember.isPresent()) {
                MemberEntity member = optionalMember.get();
                member.setUseYn("N");
                memberRepository.save(member);
                return ResponseUtil.buildResponse(ApiMessage.MEMBER_DEACTIVATED_SUCCESS, null);
            } else {
                return ResponseUtil.buildResponse(ApiMessage.MEMBER_NOT_FOUND, null);
            }
        } catch (Exception e){
            return ResponseUtil.buildResponse(ApiMessage.FAILED_TO_DEACTIVATE_MEMBER, null);
        }
    }


}

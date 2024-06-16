package com.HelloWorldTrip.TravelPlan.service;

import com.HelloWorldTrip.TravelPlan.repository.MemberEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberEntityRepository memberRepository;

    public MemberService(MemberEntityRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}

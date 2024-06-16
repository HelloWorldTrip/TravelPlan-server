package com.HelloWorldTrip.TravelPlan.service;

import com.HelloWorldTrip.TravelPlan.repository.LoginEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final LoginEntityRepository loginRepository;

    public LoginService(LoginEntityRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
}

package com.otpexample.controller;

import com.otpexample.service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private EmailVerificationService emailVerificationService;

    //http://localhost:8080/api/send-otp-for-login?email=twiliosms95@gmail.com
    @PostMapping("/send-otp-for-login")
    private Map<String , String > sendOtpForLogin(@RequestParam String email){
        return emailVerificationService.sendOtpForLogin(email);
    }

    //http://localhost:8080/api/verify-otp-for-login?email=twiliosms95@gmail.com&otp=731561
    @PostMapping("/verify-otp-for-login")
    public Map<String ,String > verifyOtpForLogin(@RequestParam String email, @RequestParam String otp){
        return emailVerificationService.verifyOtpForLogin(email,otp);
    }
}

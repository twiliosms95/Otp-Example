package com.otpexample.controller;
import com.otpexample.entity.User;
import com.otpexample.service.EmailService;
import com.otpexample.service.EmailVerificationService;
import com.otpexample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    //http://localhost:8080/api/register
    @PostMapping("/register")
    public Map<String, String> registerUser(@RequestBody User user){
        User registeredUser = userService.registerUser(user);
        emailService.sendOtpEmail(user.getEmail());

        Map<String,String> response = new HashMap<>();
        response.put("status","success");
        response.put("message","User registered successfully.Check your email for verification.");
        return response;
    }
    //http://localhost:8080/api/verify-otp
    @PostMapping("/verify-otp")
    public Map<String,String > verifyOtp(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtp(email,otp);
    }
}












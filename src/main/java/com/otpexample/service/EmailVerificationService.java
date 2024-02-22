package com.otpexample.service;

import com.otpexample.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    static final Map<String,String> emailOtpMapping = new HashMap<>();

    public Map<String,String> verifyOtp(String email,String otp){
        String storedOtp = emailOtpMapping.get(email);

        Map<String,String> response = new HashMap<>();
        if(storedOtp != null && storedOtp.equals(otp)){
            //Fetch user by email and mark email as verified
            //logger.info("OTP is valid.Proceeding with verification.");

            User user = userService.getUserByEmail(email);
            if( user!= null){
                emailOtpMapping.remove(email);
                userService.verifyEmail(user);
                response.put("status","success");
                response.put("message","Email verified Successfully");
            }else {
             //   logger.error("Invalid OTP received for Email: {}",email);
                response.put("status","error");
                response.put("message","User not found");
            }
        }
            return response;
    }

    public Map<String, String> sendOtpForLogin(String email) {
        if(userService.isEmailVerified(email)){
            String otp = emailService.generateOtp();
            emailOtpMapping.put(email,otp);

            //send otp to the user's email
            emailService.sendOtpEmail(email);

                Map<String,String> response = new HashMap<>();
                response.put("status","success");
                response.put("message","OTP sent Successfully");
                return response;
            }else{
                Map<String,String> response = new HashMap<>();
                response.put("status","error");
                response.put("message","Email is not verified");
                return response;
        }
    }

    public Map<String, String> verifyOtpForLogin(String email, String otp) {
        String storedOtp = emailOtpMapping.get(email);

        Map<String,String> response = new HashMap<>();
        if(storedOtp != null && storedOtp.equals(otp)){
            emailOtpMapping.remove(email);
            //OTP is valid
            response.put("status","success");
            response.put("message","OTP verified Successfully");
        }else{
            response.put("status","error");
            response.put("message","Invalid OTP");
            }
            return response;
    }
}

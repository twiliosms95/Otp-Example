package com.otpexample.service;

import com.otpexample.entity.User;
import com.otpexample.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user){
        return userRepository.save(user);  //saves user to the database
    }

    public User getUserByEmail(String email) {
        User user  = userRepository.findByEmail(email);
        return user;
    }

    public void verifyEmail(User user) {
        user.setEmailverified(true);
        userRepository.save(user);
    }

    public boolean isEmailVerified(String email) {
        User user = userRepository.findByEmail(email);
        return user != null && user.isEmailverified();
    }
}

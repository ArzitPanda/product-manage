package com.example.demo.services.user;

import com.example.demo.dtos.User.LoginDto;
import com.example.demo.dtos.User.SignupRequest;
import com.example.demo.models.User;

public interface UserService {
    User registerUser(SignupRequest user);
    String login(LoginDto loginDto);



    
}

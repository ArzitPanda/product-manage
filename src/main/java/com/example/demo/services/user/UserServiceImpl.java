package com.example.demo.services.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Secutiy.jwt.JwtTokenUtils;
import com.example.demo.dtos.User.LoginDto;
import com.example.demo.dtos.User.SignupRequest;
import com.example.demo.models.User;

import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImpl  implements UserService{

    @Autowired
    UserRepository userRepositories;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User registerUser(SignupRequest user) {
        if (userRepositories.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
       newUser.setPassword(user.getPassword(), passwordEncoder);
       newUser.setRoles(user.getRoles());
        return userRepositories.save(newUser);
       
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

     

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = JwtTokenUtils.generateToken(userDetails);

        return token;
    }
    
}

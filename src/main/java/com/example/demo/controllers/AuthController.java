package com.example.demo.controllers;

import com.example.demo.services.LoggedUserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.User.JwtAuthResponse;
import com.example.demo.dtos.User.LoginDto;
import com.example.demo.dtos.User.SignupRequest;
import com.example.demo.models.User;
import com.example.demo.services.user.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {



    @Autowired
    private UserService userService;


    @Autowired
    private LoggedUserContext loggedUserContext;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
        try {
            
           User user = userService.registerUser(signupRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = userService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        ResponseCookie cookie = ResponseCookie.from("accessToken", jwtAuthResponse.getAccessToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(jwtAuthResponse);
    }

    @GetMapping("authorize")
    public  ResponseEntity<User> authorize()
    {
        return  ResponseEntity.ok(loggedUserContext.getUser());
    }
    
}

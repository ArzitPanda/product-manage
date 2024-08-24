package com.example.demo.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    
    @GetMapping("test")
    public String testUser()
    {
        return "User";
    }
    
}

package com.example.demo.Secutiy;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;

@Service
public class CustomAuthService implements UserDetailsService {



  
    UserRepository userRepositories;


@Autowired
public CustomAuthService(UserRepository userRepositories)
{
    super();
    this.userRepositories=userRepositories;
   
}


    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
      
        User user = userRepositories.findByEmail(userEmail);
      
            return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.getAuthorities()
            );
    }
    
}

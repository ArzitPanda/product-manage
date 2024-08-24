package com.example.demo.config.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.Secutiy.jwt.JwtTokenUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenUtils jwtUtils;

    @Autowired
    UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String token = jwtUtils.getTokenFromRequest(request);
                if(token!=null)
                {
                    String userEmail = jwtUtils.getUserEmailFromToken(token);
                    System.out.println(userEmail);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                    if(jwtUtils.validateToken(token, userDetails))
                    {
                        System.out.println("token is valid");
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                      );
                      authenticationToken.getAuthorities().forEach(System.out::println);
                      authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                      SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                   
                        filterChain.doFilter(request, response);
                    }
                }
                else
                {
                    filterChain.doFilter(request, response);
                }

    }
    
}

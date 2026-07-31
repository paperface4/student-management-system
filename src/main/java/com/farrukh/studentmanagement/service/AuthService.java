package com.farrukh.studentmanagement.service;

import java.net.Authenticator;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import com.farrukh.studentmanagement.dto.LoginRequest;
import com.farrukh.studentmanagement.dto.LoginResponse;
import com.farrukh.studentmanagement.entity.User;


@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthService (AuthenticationManager authenticationManager,JwtService jwtService){
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

   public LoginResponse login(LoginRequest loginRequest) {

    Authentication authentication =
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
    UserDetails userDetails =(UserDetails) authentication.getPrincipal();
    String token = jwtService.generateToken(userDetails);
    return new LoginResponse(token,userDetails.getUsername(),userDetails.getAuthorities().iterator().next().getAuthority());
    }   
}

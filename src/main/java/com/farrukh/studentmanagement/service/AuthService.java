package com.farrukh.studentmanagement.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    
    public AuthService (AuthenticationManager authenticationManager){
        this.authenticationManager=authenticationManager;
    }
}

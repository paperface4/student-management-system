package com.farrukh.studentmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farrukh.studentmanagement.dto.RegisterRequest;
import com.farrukh.studentmanagement.dto.RegisterResponse;
import com.farrukh.studentmanagement.service.AuthService;
import com.farrukh.studentmanagement.service.UserService;
 
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.farrukh.studentmanagement.dto.LoginRequest;
import com.farrukh.studentmanagement.dto.LoginResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;
    private final AuthService authService;

   public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }
    @Operation(
        summary = "Login user",
        description = "Authenticates a user and returns a JWT token."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Login successful"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Validation failed because the request contains invalid data"
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Invalid username or password"
        )
})
@PostMapping("/login")
public LoginResponse login(
        @Valid @RequestBody LoginRequest request
) {
    return authService.login(request);
}
   
        @Operation(
            summary = "New user is registered",
            description = "Register a user using the provided details."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "User Register successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation failed because the request contains invalid data"
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "A user with the provided username or email already exists"
            )
    
})
@PostMapping("/register")
@ResponseStatus(HttpStatus.CREATED)
public RegisterResponse addUser(
        @Valid @RequestBody RegisterRequest request
) {
    return userService.addUser(request);
}

    
}

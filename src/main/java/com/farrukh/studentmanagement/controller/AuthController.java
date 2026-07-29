package com.farrukh.studentmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.farrukh.studentmanagement.dto.RegisterRequest;
import com.farrukh.studentmanagement.dto.RegisterResponse;
import com.farrukh.studentmanagement.service.UserService;
 
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;

   public AuthController(UserService userService) {
        this.userService = userService;
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
                    description = "A User with the provided email or roll number already exists"
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

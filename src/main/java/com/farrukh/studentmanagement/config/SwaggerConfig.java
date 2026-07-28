package com.farrukh.studentmanagement.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Student Management System API",
                version = "1.0",
                description = "REST API for managing students using Spring Boot.",
                contact = @Contact(
                        name = "Farrukh Yasin",
                        email = "farrukhyasin999@gmail.com"
                )
        )
)
public class SwaggerConfig {
}
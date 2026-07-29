package com.farrukh.studentmanagement.dto;

public class RegisterResponse {
    private Long id;
    private String userName;
    private String email;
    private String role;

      public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getUsername() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

     public void setId(Long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role=role;
    }

    public void setUsername(String userName) {
        this.userName= userName;
    }

    public void setEmail(String email) {
        this.email=email;
    }
}

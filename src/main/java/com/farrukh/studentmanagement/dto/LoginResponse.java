package com.farrukh.studentmanagement.dto;

public class LoginResponse {
    private String token;
    private String role;
    private String username;

    public String getToken(){
        return token;
    }
    public String getUsername(){
        return username;
    }
    public String getRole(){
        return role;
    }
    public void setToken(String token){
        this.token=token;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public void setRole(String role){
        this.role=role;
    }

    public LoginResponse(String token,String role,String username){
        this.token=token;
        this.username=username;
        this.role=role;
    }
}

package com.advyteam.sygha.web.dto;

import java.util.List;

public class UserDTO {
    private String email;
    private String fullname;
    private String token;
    private List<String> roles;

    public UserDTO() {
    }

    public UserDTO(String email, String fullname, String token, List<String> roles) {
        this.email = email;
        this.fullname = fullname;
        this.token = token;
        this.roles = roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

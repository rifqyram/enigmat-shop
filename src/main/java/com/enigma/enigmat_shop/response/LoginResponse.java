package com.enigma.enigmat_shop.response;

import java.util.Set;

public class LoginResponse {

    private String token;

    private Set<String> roles;

    public LoginResponse(String token, Set<String> roles) {
        this.token = token;
        this.roles = roles;
    }

    public LoginResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}

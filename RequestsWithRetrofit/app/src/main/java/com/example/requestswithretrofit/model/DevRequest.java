package com.example.requestswithretrofit.model;


public class DevRequest {
    private String username;

    public DevRequest(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
package com.msb.embeddedbanking.controller.request.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

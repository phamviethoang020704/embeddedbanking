package com.msb.embeddedbanking.facade;

import com.msb.embeddedbanking.controller.request.auth.LoginRequest;
import com.msb.embeddedbanking.controller.response.auth.LoginResponse;

public interface AuthFacade {

    LoginResponse login(LoginRequest loginRequest);

//    LoginResponse refreshToken(String refreshToken);
}

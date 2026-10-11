package com.example.empMgmtSystem.dto;

import com.example.empMgmtSystem.service.LoginService;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class LoginRequest {
    LoginService loginService;
    public LoginRequest(LoginService loginService) {
        this.loginService = loginService;
    }

    private String employeeId;

}

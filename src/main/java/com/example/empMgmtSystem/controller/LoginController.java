package com.example.empMgmtSystem.controller;

import com.example.empMgmtSystem.dto.LoginRequest;
import com.example.empMgmtSystem.dto.LoginResponse;
import com.example.empMgmtSystem.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")

public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest loginRequest,
            HttpSession session) {

        LoginResponse response =
                loginService.login(loginRequest.getEmployeeId());

        session.setAttribute("employeeId", response.getEmployeeId());
        session.setAttribute("role", response.getApplicationRole());

        return response;
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "Logged out successfully";
    }

    @GetMapping("/me")
    public LoginResponse currentSession(HttpSession session) {

        String employeeId =
                (String) session.getAttribute("employeeId");

        if (employeeId == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "No active session"
            );
        }
        return loginService.currentUser(employeeId);
    }
}
package com.example.empMgmtSystem.service;

import com.example.empMgmtSystem.dto.LoginRequest;
import com.example.empMgmtSystem.dto.LoginResponse;
import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.enums.EmployeeStatus;
import com.example.empMgmtSystem.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class LoginService {
    private EmployeeRepository employeeRepository;
    public LoginService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public LoginResponse login(String employeeId) {

        return getLoginResponse(employeeId);
    }
    public LoginResponse currentUser(String employeeId) {

        return getLoginResponse(employeeId);
    }

    private LoginResponse getLoginResponse(String employeeId) {
        Employee employee = employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found: " + employeeId)
                );

        if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {

            String hrName = employee.getAssignedHR() != null
                    ? employee.getAssignedHR().getEmployeeName()
                    : null;

            String message = (hrName != null && !hrName.isBlank())
                    ? "Your account is currently inactive. Please contact your Assigned HR. "
                    + hrName + " for assistance."
                    : "Your account is currently inactive. Please contact your HR department for assistance.";

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    message
            );
        }
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setEmployeeId(employee.getEmployeeId());
        loginResponse.setEmployeeName(employee.getEmployeeName());
        loginResponse.setApplicationRole(employee.getApplicationRole());

        return loginResponse;
    }


}

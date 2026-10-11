package com.example.empMgmtSystem.dto;

import com.example.empMgmtSystem.enums.ApplicationRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String employeeId;
    private String employeeName;
    private ApplicationRole applicationRole;
}

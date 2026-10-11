package com.example.empMgmtSystem.dto;

import com.example.empMgmtSystem.enums.ApplicationRole;
import com.example.empMgmtSystem.enums.EmployeeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeListResponse {

    private String employeeId;
    private String employeeName;
    private String email;
    private String department;
    private String jobTitle;
    private ApplicationRole applicationRole;
    private EmployeeStatus employeeStatus;
}

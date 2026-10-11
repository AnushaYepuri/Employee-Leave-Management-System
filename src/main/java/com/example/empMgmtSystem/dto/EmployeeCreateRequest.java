package com.example.empMgmtSystem.dto;

import com.example.empMgmtSystem.enums.ApplicationRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EmployeeCreateRequest {



    @NotBlank
    private String employeeName;
    @NotBlank
    private String phoneNumber;
    @NotNull
    private LocalDate joiningDate;
    @NotBlank
    private String department;
    @NotBlank
    private String jobTitle;
    @NotNull
    private ApplicationRole applicationRole;
    @NotBlank
    private String reportingManagerId;
    @NotBlank
    private String assignedHrId;

}

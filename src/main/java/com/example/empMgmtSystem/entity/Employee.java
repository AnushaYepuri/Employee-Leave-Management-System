package com.example.empMgmtSystem.entity;

import com.example.empMgmtSystem.enums.ApplicationRole;
import com.example.empMgmtSystem.enums.EmployeeStatus;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private  String employeeId;
    private  String employeeName;
    private  String phoneNumber;
    @Column(unique = true)
    private String email;
    private  LocalDate joiningDate;
    @ManyToOne
    @JoinColumn(name = "reporting_manager_id")
    private  Employee reportingManager;

    @ManyToOne
    @JoinColumn(name = "assigned_hr_id")
    private  Employee assignedHR;
    private  String department;
    private  String jobTitle;
    @Enumerated(EnumType.STRING)
    private ApplicationRole applicationRole;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;




}


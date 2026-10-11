package com.example.empMgmtSystem.controller;

import com.example.empMgmtSystem.dto.EmployeeCreateRequest;
import com.example.empMgmtSystem.dto.EmployeeListResponse;
import com.example.empMgmtSystem.dto.EmployeeResponse;
import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.service.EmployeeMappingService;
import com.example.empMgmtSystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMappingService employeeMappingService;

    public EmployeeController(EmployeeService employeeService, EmployeeMappingService employeeMappingService) {
        this.employeeService = employeeService;
        this.employeeMappingService = employeeMappingService;
    }



    @PostMapping
    public Employee createEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest){
        System.out.println("Employee create");
        Employee employee = employeeService.createEmployee(employeeCreateRequest);
        System.out.println(employee);
        return employee;
    }

    @GetMapping("/{employeeId}")
    public EmployeeResponse getEmployeeById(@PathVariable String employeeId) {
        System.out.println(employeeId);
        EmployeeResponse employee = employeeService.getEmployeeById(employeeId);
        return employee;
    }

    @GetMapping("/manager/{managerId}")
    public List<EmployeeListResponse> getEmployeesByManager(@PathVariable String managerId) {

        return employeeMappingService.getEmployeesByManager(managerId);
    }

    @GetMapping("/hr/{hrId}")
    public List<EmployeeListResponse> getEmployeesByHR(@PathVariable String hrId) {

        return employeeMappingService.getEmployeesByHR(hrId);
    }
}
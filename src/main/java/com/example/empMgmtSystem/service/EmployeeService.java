package com.example.empMgmtSystem.service;

import com.example.empMgmtSystem.dto.EmployeeResponse;
import com.example.empMgmtSystem.dto.EmployeeCreateRequest;
import com.example.empMgmtSystem.dto.EmployeeSummaryResponse;
import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.enums.ApplicationRole;
import com.example.empMgmtSystem.enums.EmployeeStatus;
import com.example.empMgmtSystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public Employee createEmployee (EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = new Employee();
        String employeeId = generateEmployeeId();
        employee.setEmployeeId(employeeId);
        employee.setEmployeeName(employeeCreateRequest.getEmployeeName());
        employee.setPhoneNumber(employeeCreateRequest.getPhoneNumber());
        employee.setJoiningDate(employeeCreateRequest.getJoiningDate());
        employee.setDepartment(employeeCreateRequest.getDepartment());
        employee.setApplicationRole(employeeCreateRequest.getApplicationRole());
        employee.setJobTitle(employeeCreateRequest.getJobTitle());

        // Find Assigned MANAGER
        Employee reportingManager = employeeRepository
                .findByEmployeeId(employeeCreateRequest.getReportingManagerId())
                .orElseThrow(() -> new RuntimeException("Reporting Manager not found"));
        if(reportingManager.getApplicationRole()== ApplicationRole.MANAGER){
            employee.setReportingManager(reportingManager);
        } else {
            throw new  RuntimeException("Selected reporting manager must have MANAGER role");
        }


        // Find Assigned HR
        Employee assignedHR = employeeRepository
                .findByEmployeeId(employeeCreateRequest.getAssignedHrId())
                .orElseThrow(() -> new RuntimeException("Assigned HR not found"));
        if(assignedHR.getApplicationRole()== ApplicationRole.HR){
            employee.setAssignedHR(assignedHR);       }
        else {
            throw new  RuntimeException("Selected HR must have HR role");
        }


        employee.setEmployeeStatus(EmployeeStatus.ACTIVE);

        return employeeRepository.save(employee);
    }

    private String generateEmployeeId() {
        Optional<Employee> optionalEmployee = employeeRepository.findTopByOrderByIdDesc();
        if (optionalEmployee.isPresent()) {
            String    lastEmployeeId = optionalEmployee.get().getEmployeeId();
            String numberPart = lastEmployeeId.substring(3);
            long number = Long.parseLong(numberPart);
            number++;
            return "EMP"+number;
        }else {

            return "EMP100001";

        }
    }
    public EmployeeResponse getEmployeeById(String employeeId) {
    Employee employee = employeeRepository.findByEmployeeId(employeeId)
            .orElseThrow(() -> new RuntimeException(
                    "Employee not found: " + employeeId
            ));
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(employee.getEmployeeId());
        employeeResponse.setEmployeeName(employee.getEmployeeName());
        employeeResponse.setEmail(employee.getEmail());
        employeeResponse.setDepartment(employee.getDepartment());
        employeeResponse.setJobTitle(employee.getJobTitle());
        employeeResponse.setApplicationRole(employee.getApplicationRole());
        employeeResponse.setEmployeeStatus(employee.getEmployeeStatus());
        // Reporting Manager
        if (employee.getReportingManager() != null) {

            Employee manager = employee.getReportingManager();

            EmployeeSummaryResponse managerResponse =
                    new EmployeeSummaryResponse();

            managerResponse.setEmployeeId(manager.getEmployeeId());
            managerResponse.setEmployeeName(manager.getEmployeeName());

            employeeResponse.setReportingManager(managerResponse);
        }

        // Assigned HR
        if (employee.getAssignedHR() != null) {

            Employee hr = employee.getAssignedHR();

            EmployeeSummaryResponse hrResponse =
                    new EmployeeSummaryResponse();

            hrResponse.setEmployeeId(hr.getEmployeeId());
            hrResponse.setEmployeeName(hr.getEmployeeName());

            employeeResponse.setAssignedHR(hrResponse);
        }

        return employeeResponse;
    }



}


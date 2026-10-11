package com.example.empMgmtSystem.service;

import com.example.empMgmtSystem.dto.EmployeeListResponse;
import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.enums.ApplicationRole;
import com.example.empMgmtSystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeMappingService{

    private final EmployeeRepository employeeRepository;

    public EmployeeMappingService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<EmployeeListResponse> getEmployeesByManager(String managerId) {

        Employee manager = employeeRepository.findByEmployeeId(managerId)
                .orElseThrow(() ->
                        new RuntimeException("Manager not found: " + managerId)
                );

        if (manager.getApplicationRole() != ApplicationRole.MANAGER) {
            throw new RuntimeException(
                    "Employee " + managerId + " is not a Manager"
            );
        }

        List<Employee> employees =
                employeeRepository.findByReportingManager(manager);

        return employees.stream()
                .map(this::convertToEmployeeListResponse)
                .toList();
    }



    public List<EmployeeListResponse> getEmployeesByHR(String hrId) {

        Employee hr = employeeRepository.findByEmployeeId(hrId)
                .orElseThrow(() ->
                        new RuntimeException("HR not found: " + hrId)
                );

        if (hr.getApplicationRole() != ApplicationRole.HR) {
            throw new RuntimeException(
                    "Employee " + hrId + " is not an HR"
            );
        }

        List<Employee> employees =
                employeeRepository.findByAssignedHR(hr);

        return employees.stream()
                .map(this::convertToEmployeeListResponse)
                .toList();
    }
    private EmployeeListResponse convertToEmployeeListResponse(Employee employee) {

        EmployeeListResponse response = new EmployeeListResponse();

        response.setEmployeeId(employee.getEmployeeId());
        response.setEmployeeName(employee.getEmployeeName());
        response.setEmail(employee.getEmail());
        response.setDepartment(employee.getDepartment());
        response.setJobTitle(employee.getJobTitle());
        response.setApplicationRole(employee.getApplicationRole());
        response.setEmployeeStatus(employee.getEmployeeStatus());

        return response;
    }

}

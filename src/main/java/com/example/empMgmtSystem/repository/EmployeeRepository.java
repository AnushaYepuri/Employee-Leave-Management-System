
package com.example.empMgmtSystem.repository;

import com.example.empMgmtSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(String employeeId);

    boolean existsByEmployeeId(String employeeId);

    Optional<Employee> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<Employee> findTopByOrderByIdDesc();

    List<Employee> findByReportingManager(Employee reportingManager);

    List<Employee> findByAssignedHR(Employee assignedHR);
}

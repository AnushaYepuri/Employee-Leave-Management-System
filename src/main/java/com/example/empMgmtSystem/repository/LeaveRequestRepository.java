package com.example.empMgmtSystem.repository;

import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.entity.LeaveRequest;
import com.example.empMgmtSystem.enums.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findByEmployeeAndStatus(
            Employee employee,
            LeaveStatus status
    );

    List<LeaveRequest> findByStatus(LeaveStatus status);



    List<LeaveRequest> findByEmployee_ReportingManager_IdAndStatus(
            Long managerId,
            LeaveStatus status
    );
}
package com.example.empMgmtSystem.repository;

import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {

    Optional<LeaveBalance> findByEmployee(Employee employee);

    Optional<LeaveBalance> findByEmployeeId(Long employeeId);
}
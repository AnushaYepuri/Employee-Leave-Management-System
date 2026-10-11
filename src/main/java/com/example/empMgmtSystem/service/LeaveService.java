package com.example.empMgmtSystem.service;

import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.entity.LeaveBalance;
import com.example.empMgmtSystem.entity.LeaveRequest;
import com.example.empMgmtSystem.enums.LeaveStatus;
import com.example.empMgmtSystem.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveService {

    private final EmployeeRepository employeeRepository;
    private final LeaveBalanceRepository leaveBalanceRepository;
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveService(
            EmployeeRepository employeeRepository,
            LeaveBalanceRepository leaveBalanceRepository,
            LeaveRequestRepository leaveRequestRepository) {

        this.employeeRepository = employeeRepository;
        this.leaveBalanceRepository = leaveBalanceRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    public List<LeaveRequest> getPendingLeavesForManager(Long managerId) {

        return leaveRequestRepository
                .findByEmployee_ReportingManager_IdAndStatus(
                        managerId,
                        LeaveStatus.PENDING
                );
    }

    public LeaveRequest applyLeave(
            Long employeeId,
            String leaveType,
            LocalDate startDate,
            LocalDate endDate,
            String reason) {

        // 1. Find employee
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        // 2. Validate dates
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException(
                    "Start date cannot be after end date");
        }

        // 3. Find leave balance
        LeaveBalance balance = leaveBalanceRepository
                .findByEmployeeId(employeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Leave balance not found"));

        // 4. Calculate number of leave days
        long leaveDays =
                java.time.temporal.ChronoUnit.DAYS
                        .between(startDate, endDate) + 1;

        // 5. Check available balance
        if (leaveDays > balance.getRemainingLeaves()) {
            throw new RuntimeException(
                    "Insufficient leave balance");
        }

        // 6. Create leave request
        LeaveRequest leaveRequest = new LeaveRequest();

        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setReason(reason);

        // 7. New requests are always PENDING
        leaveRequest.setStatus(LeaveStatus.PENDING);

        // 8. Store request time
        leaveRequest.setRequestedAt(LocalDateTime.now());

        // 9. Save
        return leaveRequestRepository.save(leaveRequest);
    }
    public LeaveRequest approveLeave(Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveRequestRepository.findById(leaveRequestId)
                        .orElseThrow(() ->
                                new RuntimeException("Leave request not found"));

        if (leaveRequest.getStatus() != LeaveStatus.PENDING) {
            throw new RuntimeException(
                    "Only pending leave requests can be approved");
        }

        LeaveBalance balance =
                leaveBalanceRepository
                        .findByEmployeeId(
                                leaveRequest.getEmployee().getId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Leave balance not found"));

        long leaveDays =
                java.time.temporal.ChronoUnit.DAYS.between(
                        leaveRequest.getStartDate(),
                        leaveRequest.getEndDate()
                ) + 1;

        if (leaveDays > balance.getRemainingLeaves()) {
            throw new RuntimeException(
                    "Insufficient leave balance");
        }

        balance.setUsedLeaves(
                balance.getUsedLeaves() + (int) leaveDays
        );

        leaveRequest.setStatus(LeaveStatus.APPROVED);

        leaveBalanceRepository.save(balance);

        return leaveRequestRepository.save(leaveRequest);
    }
}
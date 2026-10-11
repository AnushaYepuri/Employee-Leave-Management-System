package com.example.empMgmtSystem.controller;

import com.example.empMgmtSystem.dto.LeaveRequestCreateDTO;
import com.example.empMgmtSystem.entity.LeaveRequest;
import com.example.empMgmtSystem.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public ResponseEntity<LeaveRequest> applyLeave(
            @RequestBody LeaveRequestCreateDTO request) {

        LeaveRequest leaveRequest = leaveService.applyLeave(
                request.getEmployeeId(),
                request.getLeaveType(),
                request.getStartDate(),
                request.getEndDate(),
                request.getReason()
        );

        return ResponseEntity.ok(leaveRequest);
    }
    @GetMapping("/manager/{managerId}/pending")
    public ResponseEntity<List<LeaveRequest>> getPendingLeavesForManager(
            @PathVariable Long managerId) {

        List<LeaveRequest> leaves =
                leaveService.getPendingLeavesForManager(managerId);

        return ResponseEntity.ok(leaves);
    }
    @PutMapping("/{leaveRequestId}/approve")
    public ResponseEntity<LeaveRequest> approveLeave(
            @PathVariable Long leaveRequestId) {

        LeaveRequest leaveRequest =
                leaveService.approveLeave(leaveRequestId);

        return ResponseEntity.ok(leaveRequest);
    }
}
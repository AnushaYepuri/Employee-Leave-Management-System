package com.example.empMgmtSystem.config;

import com.example.empMgmtSystem.entity.Employee;
import com.example.empMgmtSystem.enums.EmployeeStatus;
import com.example.empMgmtSystem.repository.EmployeeRepository;
import tools.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginSessionInterceptor implements HandlerInterceptor {

    private final EmployeeRepository employeeRepository;
    private final ObjectMapper objectMapper;

    public LoginSessionInterceptor(
            EmployeeRepository employeeRepository,
            ObjectMapper objectMapper) {
        this.employeeRepository = employeeRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null ||
                session.getAttribute("employeeId") == null) {
            return reject(response, 401,
                    "Your session has expired. Please log in again.");
        }

        String employeeId =
                (String) session.getAttribute("employeeId");

        Employee employee = employeeRepository
                .findByEmployeeId(employeeId)
                .orElse(null);

        if (employee == null) {
            session.invalidate();
            return reject(response, 401,
                    "Employee not found. Please log in again.");
        }

        if (employee.getEmployeeStatus() != EmployeeStatus.ACTIVE) {
            String hrName = employee.getAssignedHR() != null
                    ? employee.getAssignedHR().getEmployeeName()
                    : null;

            String message = (hrName != null && !hrName.isBlank())
                    ? "Your account is currently inactive. "
                    + "Please contact your Assigned HR, "
                    + hrName + ", for assistance."
                    : "Your account is currently inactive. "
                    + "Please contact your HR department for assistance.";

            session.invalidate();

            return reject(response, 403, message);
        }

        return true;
    }

    private boolean reject(
            HttpServletResponse response,
            int status,
            String message) throws Exception {

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(
                response.getWriter(),
                Map.of("status", status, "message", message)
        );

        return false;
    }
}

# 🏢 Employee Leave Management System

### 🚀 A Spring Boot Backend Application for Employee Leave and Approval Management

A Java-based Employee Leave Management System built using **Spring Boot, Spring Data JPA, Hibernate, and MySQL** to manage employee leave requests, approval workflows, leave balances, and employee information through REST APIs.

This project focuses on solving a common organizational problem by replacing manual leave management processes with a structured, database-driven application.

---

## 📌 Table of Contents

- 🎯 Project Overview
- 💡 Why I Chose This Project
- ❗ Problem Statement
- ✨ Key Features
- 🔄 Application Workflow
- 🛠️ Technology Stack
- 🏗️ Project Architecture
- 💻 My Contributions
- 🧠 Skills Demonstrated
- 🏆 Key Highlights and USPs
- 📚 Challenges and Learnings
- 🚀 Future Enhancements
- ⚙️ How to Run the Project
- 👨‍💻 Author

---

## 🎯 Project Overview

The Employee Leave Management System is designed to simplify how employees request leave and how managers review and process those requests.

The application uses a layered backend architecture to separate business logic, API handling, data access, and database persistence.

The primary objective was to build a practical backend project that demonstrates how Java and Spring Boot can be used to develop maintainable, database-driven business applications.

## 💡 Why I Chose This Project

There are many common projects, such as e-commerce applications, banking systems, and library management systems. I chose an Employee Leave Management System because it allowed me to work on a complete business workflow rather than focusing only on basic CRUD operations.

I wanted to understand how a real organization manages employee requests, approval processes, employee relationships, and leave-related business rules.

Through this project, I focused on developing backend functionality, designing REST APIs, managing relational data, and understanding how different application components work together.

**My goal was not just to build an application that stores data, but to build one that follows meaningful business rules and reflects real workplace processes.**

## ❗ Problem Statement

Traditional leave management processes may rely on emails, spreadsheets, or manual approvals. These approaches can make it difficult to track requests, maintain accurate leave balances, and manage approval responsibilities.

This project aims to provide a centralized system in which:

- 👤 Employees can submit leave requests.
- 📋 Leave requests can be tracked through their statuses.
- 👨‍💼 Managers can review and approve or reject requests.
- 🗄️ Employee and leave information can be maintained in a relational database.
- 🔐 Login and application roles support access to the relevant functionality.

## ✨ Key Features

### 👤 Employee Management
- Store and manage employee information.
- Maintain employee records in MySQL.
- Use JPA entities and repositories for database operations.

### 📝 Leave Request Management
- Submit leave requests through REST APIs.
- Track requests using statuses such as `PENDING`, `APPROVED`, and `REJECTED`.
- Maintain leave request records in the database.

### 👨‍💼 Leave Approval Workflow
- Support the manager review process.
- Process leave approval and rejection.
- Associate leave requests with the relevant employee and approval workflow.

### 🏖️ Leave Balance Management
- Maintain leave balance information.
- Support leave-related business rules.
- Provide a foundation for validating leave availability and tracking leave usage.

### 🔐 Login and Role Management
- Implement login functionality.
- Use employee and application-role information to support role-specific workflows.
- Provide a foundation for protecting employee and manager operations.

> Note: The exact availability of individual features depends on the current implementation. Only mark a feature as completed after verifying its API and business logic.

## 🔄 Application Workflow

1. 👤 An employee logs in.
2. 📝 The employee submits a leave request.
3. 🗄️ The application validates and stores the request.
4. ⏳ The request enters the pending approval workflow.
5. 👨‍💼 The manager reviews the request.
6. ✅ The request is approved or ❌ rejected.
7. 📊 The request status and relevant leave records are updated according to the business rules.

## 🛠️ Technology Stack

| Technology | Purpose |
|---|---|
| ☕ Java 17 | Backend programming |
| 🌱 Spring Boot | Application development |
| 🔗 Spring Web | REST API development |
| 🗃️ Spring Data JPA | Database access and persistence |
| 🔄 Hibernate | Object-relational mapping |
| 🐬 MySQL | Relational database |
| 📦 Maven | Dependency and build management |
| 🧪 Postman | API testing |
| 🌿 Git and GitHub | Version control and source code management |

## 🏗️ Project Architecture

The application follows a layered architecture to improve separation of concerns, maintainability, and testability.

```text
Client / Postman
       |
       v
Controller Layer
       |
       v
Service Layer
       |
       v
Repository Layer
       |
       v
MySQL Database
```

### Responsibilities of Each Layer

- **Controller:** Exposes REST endpoints and handles HTTP requests and responses.
- **Service:** Contains business logic and coordinates application operations.
- **Repository:** Performs database access using Spring Data JPA.
- **Entity:** Represents persistent data and database relationships.
- **DTO:** Transfers request and response data between API clients and the application.

## 💻 My Contributions

I worked on the backend development of this project, focusing on the following areas:

- Designed and worked with Java entity classes.
- Developed repository interfaces using Spring Data JPA.
- Worked with relational database tables and SQL queries.
- Implemented employee login functionality.
- Developed leave application and approval API workflows.
- Used HTTP methods and REST endpoints to handle application operations.
- Organized application code into separate layers.
- Worked with entity relationships and request/response data.
- Used Git and GitHub to manage and publish source code.

## 🧠 Skills Demonstrated

### Backend Development
- Core Java
- Object-Oriented Programming
- Spring Boot
- Spring Web and REST APIs
- Spring Data JPA
- Hibernate

### Database Management
- MySQL
- SQL queries
- Relational data modeling
- Entity relationships
- Database persistence

### Software Engineering
- Layered architecture
- Business logic implementation
- Request and response handling
- API testing
- Debugging and troubleshooting
- Version control with Git

## 🏆 Key Highlights and USPs

### ⭐ 1. Business Workflow Instead of Basic CRUD Alone
The project focuses on a real approval workflow involving employees, leave requests, and managers, rather than only creating and retrieving records.

### ⭐ 2. Practical Backend Architecture
The separation of controllers, services, repositories, entities, and DTOs demonstrates an understanding of maintainable Spring Boot application design.

### ⭐ 3. Database-Driven Application
The application uses MySQL and Spring Data JPA to persist employee and leave-related information.

### ⭐ 4. Role-Aware Functionality
The project incorporates login and application roles as a foundation for employee and manager workflows.

### ⭐ 5. Scope for Real-World Business Rules
The design can be extended to support leave balance validation, delegated approvals, pending-request checks, and employee administration.

## 📚 Challenges and Learnings

While developing this project, I gained practical experience in:

- Understanding how requests flow through a Spring Boot application.
- Separating business logic from controller and database code.
- Mapping Java entities to relational database tables.
- Working with REST API methods and HTTP status codes.
- Debugging Git and application configuration issues.
- Understanding how database relationships support business workflows.
- Recognizing the importance of validation, authorization, and consistent data updates.

This project helped me move beyond writing individual Java programs and understand how multiple backend components work together in an application.

## 🚀 Future Enhancements

- 🔒 Add comprehensive role-based authorization.
- 📊 Build dashboards for employees, managers, and HR.
- 🏖️ Implement configurable leave policies and leave balance calculations.
- 🔔 Add email notifications for leave decisions.
- 📅 Add holiday calendars and leave overlap validation.
- 🧪 Expand automated unit and integration testing.
- 🐳 Containerize the application using Docker.
- ☁️ Deploy the application to a cloud platform.

## ⚙️ How to Run the Project

### Prerequisites

- Java 17 or a compatible configured JDK
- Maven
- MySQL Server
- Git

### Steps

1. Clone the repository:

   ```bash
   git clone https://github.com/AnushaYepuri/Employee-Leave-Management-System.git
   ```

2. Open the project in your preferred Java IDE.

3. Create a MySQL database for the application.

4. Configure the database connection using your local configuration or environment variables.

5. Build the application:

   ```bash
   mvn clean install
   ```

6. Run the Spring Boot application through your IDE or Maven.

7. Test the available REST endpoints using Postman.

**Important:** Configure your own database credentials locally. Never commit passwords or other secrets to GitHub.

## 👩‍💻 Author

**Anusha Yepuri**

Associate Software Engineer | Java Backend Development

Interested in Java, Spring Boot, REST APIs, database design, and building reliable backend applications.

🔗 GitHub: [AnushaYepuri](https://github.com/AnushaYepuri)

---

⭐ If you find this project useful, feel free to explore the repository and its implementation.

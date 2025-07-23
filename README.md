# Job Vacancy Manager Frontend

## Overview
Job Vacancy Manager Frontend is a web application built with Spring Boot and Thymeleaf that serves as the frontend for a job vacancy management system. It allows candidates to search and apply for jobs, and companies to post and manage job listings. The application provides authentication and role-based access for both candidates and companies.

## Features

### For Candidates
- Register and create a candidate profile
- Login and manage personal profile
- Search for job vacancies with filters
- Apply for jobs directly from the platform

### For Companies
- Register and create a company profile
- Login and manage company profile
- Post new job vacancies
- List and manage all jobs posted by the company

## Technologies Used
- **Java 21**
- **Spring Boot 3.5.3**
- **Spring Security** (role-based authentication)
- **Thymeleaf** (server-side template engine)
- **Lombok** (boilerplate code reduction)
- **Maven** (build and dependency management)

## Project Structure
- `modules/candidate/` - Candidate-related controllers, services, and DTOs
- `modules/company/` - Company-related controllers, services, and DTOs
- `security/` - Security configuration
- `utils/` - Utility classes
- `resources/templates/` - Thymeleaf HTML templates for candidate and company views

## Configuration
- The application runs on port `8082` by default.
- It communicates with a backend API (default: `http://localhost:8080`) for job and user management.

## Getting Started
1. Clone the repository
2. Build the project with Maven:
   ```bash
   ./mvnw clean install
   ```
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the app at [http://localhost:8082](http://localhost:8082)
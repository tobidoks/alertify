# Alertify Project

---
## Overview
Alertify is a powerful task management and user authentication system designed using Java, Spring Boot, and PostgreSQL. This project allows users to manage tasks, track their progress, and manage user profiles with secure authentication. It's built to provide an intuitive API for task management, enabling easy integration and usage.


---

## Prerequisites

Ensure you have the following installed:
- **Java 23**
- **Maven 4.x**
- **Docker**

---

## Setup Instructions

### 1. Clone the Repository

Clone the repository from GitHub to your local machine:
```bash
git clone https://github.com/tobidoks/alertify.git
cd alertify
```

---

### 2. Run Docker-Compose to Start Postgres Container

Ensure Docker is running on your machine. Then, execute the following command to start the PostgresDB container:
```bash
docker-compose up -d
```
This will pull the Postgres Docker image (if not already present) and start the container in detached mode.

---

### 3. Build the Project

Run the following command to build the project using Maven:
```bash
mvn clean install
```
This will compile the source code, run tests, and create a build artifact.

---

### 4. Configure Environment Variables

1. Add the necessary environment variables to the Spring Boot application configuration
2. Add an active profile and set it to `local`


---

### 5. Run the Project in Your IDE

1. Open the project in your IDE
2. Run the `AlertifyApplication` class to start the Spring Boot application.

---

## API Documentation

Once the application starts, it will be accessible at:
```
http://localhost:8080/swagger-ui/index.html
```
Use this URL to explore the API endpoints with Swagger UI.
The Swagger UI provides a graphical interface to explore and test the API endpoints.

---

## Technologies Used

- **Java 23**: Programming language for application logic.
- **Spring Boot**: Framework for building the backend application.
- **PostgresDB**: PostgresSQL database for data persistence.
- **Maven**: Build and dependency management tool.
- **Docker**: Containerization platform for running PostgresDB.
- **Swagger**: Tool for API documentation and testing.

---


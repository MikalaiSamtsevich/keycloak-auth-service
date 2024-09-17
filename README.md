# Keycloak Authentication Service

This project integrates with **Keycloak** for authentication and authorization, serving as an admin server to manage
users, roles, and groups within the Keycloak realm. It leverages the **Keycloak Admin Client** for communication and
management of these entities. The project is built with **Spring Boot** and secures resources using **OAuth 2.0** and *
*Spring Security**.

## Description

The Keycloak Authentication Service is a Spring Boot-based project that acts as an authorization server and admin
service. It is responsible for managing Keycloak users, roles, and groups via the **Keycloak Admin Client API**. It also
integrates **Spring Security** to secure the microservice with OAuth 2.0 standards.

## Features

- **User Management**: Add, update, delete users in Keycloak.
- **Role Management**: Assign and manage user roles.
- **Group Management**: Manage user groups in Keycloak.
- **OAuth 2.0 Authorization**: Implements OAuth 2.0 authorization server for security.
- **Keycloak Admin Client**: Provides interaction with the Keycloak Admin API for resource management.

## Technologies Used

- **Java 21**: The project uses Java 21 as the programming language.
- **Spring Boot 3.3.1**: Spring Boot framework is used for rapid application development.
- **Keycloak 25.0.0**: Integrated with Keycloak for authentication and authorization.
- **OAuth 2.0**: Implements an authorization server to secure endpoints and resources.
- **Keycloak Admin Client**: Used to interact with Keycloak's API to manage users, roles, and groups.

### Key Points:

- **Spring Boot OAuth2 Authorization Server**: Used to set up an OAuth 2.0 Authorization Server.
- **Spring Security**: Provides security for the application.
- **Keycloak Admin Client**: Allows the service to interact with Keycloak’s Admin API.
- **Lombok**: Reduces boilerplate code (optional and excluded from the build).

## Project Structure

- **Admin Server**: Acts as an admin interface to manage users, roles, and groups.
- **Keycloak Integration**: Uses Keycloak Admin Client for direct interaction with Keycloak.
- **Security**: The application is secured using **Spring Security** and **OAuth 2.0** for authorization.

## Getting Started

### Prerequisites

- Java 21
- Maven 3.6+
- Docker (optional for containerization)
- A running instance of **Keycloak** with admin credentials

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/keycloak-auth-service.git
   cd keycloak-auth-service
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

### Running the Application

1. Use docker-compose to start postgres and keycloak
   ```bash
   mvn spring-boot:run
   ```
2. Run app

   ```bash
   ./mvnw spring-boot:run
   ```

### API Endpoints

The service exposes several RESTful endpoints for managing users, roles, and groups. Here are a few sample endpoints:

- **Create User**: `POST /users`
- **Assign Role to User**: `POST /users/{userId}/roles`
- **Create Group**: `POST /groups`

## Keycloak Integration

The service communicates with **Keycloak Admin API** to manage users, roles, and groups. It relies on the **Keycloak
Admin Client** to handle all interactions. This allows the service to be used as an administrative tool for user
management in the Keycloak realm.
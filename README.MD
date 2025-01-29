# Forum Application

## Overview

This project is a forum application built using Java, Spring Boot, and Maven. It allows users to create, update, and
view topics, posts, and comments. The application also includes user authentication and authorization features.

## Features

- User Management: Create, update, and authenticate users.
- Topic Management: Create, update, and view topics.
- Post Management: Create, update, and view posts within topics.
- Comment Management: Create and view comments on posts.
- Exception Handling: Custom exceptions for handling missing input data and non-existing entities.
- Pagination: Support for paginated retrieval of topics, posts, and comments.

## Technologies Used

- Java: Programming language used for the application.
- Spring Boot: Framework for building the application.
- Maven: Build automation tool.
- JPA: Java Persistence API for database operations.
- Lombok: Library to reduce boilerplate code.
- JUnit: Testing framework.
- MapStruct: Annotation processor for generating type-safe bean mappers.
- Flyway: Database migration tool.
- PostgreSQL: Database management system.

## Project Structure

- `src/main/java/com/forum/controller`: Contains the REST controllers for handling HTTP requests.
- `src/main/java/com/forum/service`: Contains the service classes for business logic.
- `src/main/java/com/forum/repository`: Contains the repository interfaces for database operations.
- `src/main/java/com/forum/data/dto`: Contains the Data Transfer Objects (DTOs).
- `src/main/java/com/forum/data/entity`: Contains the entity classes representing the database tables.
- `src/main/java/com/forum/exceptions`: Contains custom exception classes.
- `src/main/java/com/forum/data/mapping`: Contains the mapper classes for converting between entities and DTOs.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- PostgreSQL 17 or higher

### Installation

1. Clone the repository:
   ```sh
   git clone    https://github.com/YanitsaNikolova/forum-be-main.git

# Rental Backend

Rental Backend is a RESTful service built with Spring Boot, designed to manage and provide information about rentals.

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation and Usage](#installation-and-usage)
- [API Documentation with Swagger](#api-documentation-with-swagger)

## Introduction
Rental Backend offers a robust and flexible API to manage various aspects of rentals. Whether you wish to fetch information about a specific rental or add a new rental to the database, Rental Backend has got you covered.

## Features
- Full CRUD for rentals.

## Technologies Used
- **Spring Boot**: Eases the creation of Spring-based applications.
- **Spring Data JPA**: Simplifies the setup of data persistence layers.
- **Springfox Swagger**: Provides interactive API documentation.
- **Spring Security**: Ensures the security of the application and user authentication.
- **MySQL**: The database used to store information.

## Installation and Usage

### Prerequisites

- Java 11 or later
- Gradle
- MySQL

### Installation Steps

1. **Clone the Repository**
    ```bash
    git clone https://github.com/jeremyMulet/rentalBackend
    ```

2. **Build the Project**  
   Navigate to the project directory:
    ```bash
    cd rentalBackend
    ```
   Then build it:
    ```bash
    gradle clean build
    ```

3. **Configure the Database**  
   Set up your MySQL database and update the `application.properties` file with your connection details.

4. **Start the Application**  
   You can start the application in one of the following ways:
    - Using Gradle:
        ```bash
        ./gradlew bootRun
        ```

5. **Access the API**  
   Once the application starts, it should be accessible at the following address:  
   `http://localhost:8080/`

## Setting up the Database

To initialize the database, follow the steps below:

### Execute the SQL Script

1. **Locate the SQL Script**  
   The initialization script `script.sql` is located in the project's `resources` package.

2. **Use Command Line**  
   If you prefer using the command line, you can do so as follows:

    ```bash
    mysql -u [username] -p rental_db < path_to_your_project/resources/script.sql
    ```
    - Replace `[username]` with your MySQL username.
    - Make sure the path (`path_to_your_project`) points to the main directory of your project.

3. **Use a GUI Tool**  
   If you're using a tool like MySQL Workbench:
    - Open the `script.sql` file directly from the tool.
    - Execute the script to initialize tables and relationships.

### Verification

After executing the script, ensure that all tables are properly created and relationships are in place.

## API Documentation with Swagger

After starting the application, the interactive Swagger documentation is available at: `http://localhost:8080/swagger-ui/`.

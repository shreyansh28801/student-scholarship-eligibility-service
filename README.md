# Student Scholarship Eligibility Service

## Description
This project is a microservice-based API designed to determine the scholarship eligibility of students based on their marks. It processes CSV files containing student data and updates their eligibility status based on dynamically configurable criteria.

## Features
- **Upload CSV**: Users can upload CSV files (up to 50,000 records) via a Swagger endpoint.
- **Dynamic Criteria**: Eligibility criteria can be updated dynamically through an API endpoint.
- **Status Check**: Retrieve a student's scholarship eligibility status using their roll number.
- **Multi-threading**: Utilizes multi-threading for efficient CSV file processing.
- **In-memory DB/Cache**: Supports quick searches using in-memory database or cache.

## Technology Stack
- **Java**: Version 17
- **Spring Boot**: Version 3.3.0
- **Maven**: For project management
- **Swagger**: For API documentation
- **H2 Database**: In-memory database for quick access and storage

## Project Structure
```bash
student-service
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── studentservice
│   │   │               ├── controller
│   │   │               ├── entity
│   │   │               ├── repository
│   │   │               └── service
│   │   └── StudentServiceApplication.java
│   ├── resources
│       └── application.properties
│   └── logback-spring.xml
├── test
│   ├── java
│   │   └── com
│   │       └── example
│   │           └── studentservice
│   │               └── controller
│   │                   └── StudentControllerTests.java
└── pom.xml
```


## Setup and Running the Project
1. **Clone the repository**
    ```bash
    git clone https://github.com/your-repository/student-service.git
    cd student-service
    ```

2. **Build the project**
    ```bash
    mvn clean install
    ```

3. **Run the application**
    ```bash
    mvn spring-boot:run
    ```

4. **Access Swagger UI**
    Open your browser and navigate to `http://localhost:8080/swagger-ui.html` to access the API documentation and test endpoints.

## Configuration
**application.properties**
```properties
# Logging
logging.level.org.springframework=INFO
logging.level.com.example.studentservice=DEBUG

# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

##Running Tests
```bash
    mvn test
```

###Swagger Snap 
![Uploading Screenshot 2024-05-29 at 8.42.11 PM.png…]()

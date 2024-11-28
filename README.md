# Estate

**Description**:  
This project is the backend of Estate, an online portal app that enables potential tenants to contact property owners for the various properties they wish to rent.


## **Architecture**
This project follows the **MVC architecture**:

- **Configuration**: Contains authentication configuration with SpringSecurity.

- **Controller**: Handles incoming HTTP requests and maps them to the appropriate services or actions. It serves as the entry point for the API endpoints.

- **DTO** (Data Transfer Objects): Defines the objects used to transfer data between layers, ensuring that only necessary data is exposed and used.

- **Mapper**: Provides mapping logic to convert between DTOs and models, simplifying the interaction between the API and the database.

- **Model**: Represents the core entities in the application, typically mapping to database tables.

- **Repository**: Interfaces responsible for data access, performing CRUD operations on the database using Spring Data JPA.

- **Service**: Contains business logic and interacts with repositories to perform required operations for the controllers.


## **Prerequisites**
Before you begin, ensure you have the following installed:

- **Java**: Version 17 or later  
  [Download Java](https://www.oracle.com/java/technologies/javase-downloads.html).

- **Git**: Version 2.38.1 or later  
  [Download Git](https://git-scm.com/).

- **IntelliJ IDEA** or another IDE:  
  [Download IntelliJ IDEA](https://www.jetbrains.com/idea/).

## **Configuration**
You need to add environement variables into estate/src/main/resources/application.proporties :  

**DBUrl**: url of your database.  
**DBUsername**: username of your database.  
**DBPassword**: password of your database.  
**MyJwtKey**: a jwt key who can be generate in a web site ([JWT.io](https://jwt.io/)).  
**ProjectPath**: the path where you have clone the project without user root.

## **Installation**
Follow these steps to install the project:

1. **Clone the repository**
   ```bash
   git clone https://github.com/mysmallworld/Cheikh_Ines_P3_092024.git

2. **Navigate to the project directory**
   ```bash
   cd Cheikh_Ines_P3_092024
   ```

3. **Build the project using Maven**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## Development server  
The development server runs at: `http://localhost:3001/`. 
Reload the application if you make any changes to the source files.

## Environment  
The project uses the following key technologies:

**SpringBoot**: A framework for building microservices and REST APIs.  
**SpringSecurity**: Ensures secure authentication and authorization within the application.  
**MySQL**: A relational database used to persist application data.   
**Lombok**: A Java library to minimize boilerplate code.

## Versions
- **Git**: 2.38.1
- **IntelliJ**: 2024
- **Java**: 17
- **SpringBoot**: 3.3.5
- **SpringSecurity**: 3.3.5
- **MySql**: 8.0.33
- **Lombok**: 1.18.34
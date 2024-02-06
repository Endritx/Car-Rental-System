# Car Rental

Welcome to my Car Rental project.

This is a simple springboot application about a car rental company.
The main reason for me to create this application was to learn Springboot application and the database(backend) I am still learning to do the API and frontend part of this project and I started doing it with Angular.
 

# Used Tools

- Java 
- Spring Boot
- Maven 
- MySQL Community Server 
- Spring Data JPA
- Spring Web MVC
- Spring Security with JWT
- Lombok
- JUnit 

# How to run the app

1. Connect with database you want to use it (MySQL, Oracle, Mongo DB etc).
 The code to connect with database:

   ```` 
   spring.datasource.url=jdbc:mysql://localhost:3308/CarRental 
   spring.datasource.username=root 
   spring.datasource.password='' 
   spring.jpa.hibernate.ddl-auto=create 
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect 
   spring.jpa.show-sql=true
   or
   spring:
   datasource: 
   url: jdbc:mysql://localhost:3306/CarRental
   username: root
   password:
   jpa:
   hibernate:
   ddl-auto: update
    properties:
    hibernate:
    dialect: org.hibernate.dialect.MySQL8Dialect
    format_sql: true
    show-sql: true

_This part: jdbc:mysql://localhost:3308/CarRental : 3308 is the Configured Port. CarRental is the name of my database
Type username and password if you have one.
create does the creation of all entites, attributes done in the code if so._


3. In terminal, execute the command to get my code : `git clone https://github.com/Endritx/CarRental.git`
4. Run the Springboot application and MySQL Server. Insert values with Get and Post method or directly by SQL code in the database.
5. The API and the front-end I am doing with angular and when it is connected it runs on server http://localhost:4200/ , but It is not finished :).

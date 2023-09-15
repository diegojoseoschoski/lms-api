# lms-api
This api supports the lms project using Java Spring Boot 

## Requirements

For building and running the application you need:

- [JDK 11]
- [Maven 3]

## Steps to run
1. Build the project using
  `mvn clean install`
2. Run using `mvn spring-boot:run`
3. The web application is accessible via http://localhost:8080
   

## Documentation
Acess the swagguer documentation via http://localhost:8080/swagger-ui/index.html

## Notes
- To see the database records you can acess via http://localhost:8080/h2-console
- datasource jdbc:h2:file:./lmsdb
- username: sa



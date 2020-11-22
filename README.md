# SpringBootExample

- Open in IntelliJ
- Alter src/main/resources/application.properties to reflect your own database setup (database url, username, password etc.)
- Run the bootRun application gradle task to run the api - you should then be able to post and get tasks and localhost:8080/api/tasks
- Run the test verification gradle task to run tests

## How it works

- This API uses the spring-boot-starter-data-rest Spring Boot libraries in order to automatically generate a controller with REST API endpoints
- By adding methods to TaskRepository, additional endpoints can be generated
- TaskRepository subclasses PagingAndSortingRepository and therefore automatically includes paging and sorting query parameters for the API endpoints
- Uses [Spring Data JPA](https://spring.io/projects/spring-data-jpa) to reduce boilerplate code and automatically produce database SQL queries 

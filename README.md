# Spring Boot "PlusOne" Project

This is a Java / Maven / Spring Boot (version 3.0.2) application that can be used as a starter for creating a microservice complete with built-in health check, metrics and much more. I hope it helps you.

## How to Run

This application is deployed in Heroku.com For connection details, please refer to
follows application.yml

* Clone this repository
* You can build the project and run the tests by running 
```
mvn clean package
mvn test package
```
* Once successfully built, you should see something like this:
```agsl
[INFO] Scanning for projects...
[INFO] 
[INFO] -------------------------< com.example:demo1 >--------------------------
[INFO] Building demo1 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-clean-plugin:3.2.0:clean (default-clean) @ demo1 ---
[INFO] Deleting /Users/tylerzhang/2023/spring/plus1-server/PlusOne/target
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  0.173 s
[INFO] Finished at: 2023-04-23T20:19:57-07:00
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0

```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs test you should see something like this
```
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.021 s - in com.example.services.ActivityServiceTest
2023-04-23T20:21:04.932-07:00  INFO 61382 --- [ionShutdownHook] j.LocalContainerEntityManagerFactoryBean : Closing JPA EntityManagerFactory for persistence unit 'default'
2023-04-23T20:21:05.026-07:00  INFO 61382 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown initiated...
2023-04-23T20:21:06.398-07:00  INFO 61382 --- [ionShutdownHook] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Shutdown completed.
[INFO] 
[INFO] Results:
[INFO] 
[INFO] Tests run: 63, Failures: 0, Errors: 0, Skipped: 0
[INFO] 
[INFO] 
[INFO] --- jacoco-maven-plugin:0.8.7:report (report) @ demo1 ---
[INFO] Loading execution data file /Users/tylerzhang/2023/spring/plus1-server/PlusOne/target/jacoco.exec
[INFO] Analyzed bundle 'demo1' with 7 classes
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  10.223 s
[INFO] Finished at: 2023-04-23T20:21:06-07:00
[INFO] ------------------------------------------------------------------------

Process finished with exit code 0

```

## About the Service

The service is just a simple activity search REST service. It uses MySQL database to store the data. You can also do with a relational database like MySQL or PostgreSQL. If your database connection properties work, you can call some REST endpoints defined in ```com.khoubyari.example.api.rest.hotelController``` on **port 8090**. (see below)

More interestingly, you can start calling some of the operational endpoints (see full list below) like ```/activites``` and ```/health``` (these are available on **port 8091**)

You can use this sample service to understand the conventions and configurations that allow you to create a DB-backed RESTful service. Once you understand and get comfortable with the sample app you can add your own services following the same patterns as the sample service.

Here is what this little application demonstrates:

* Full integration with the latest **Spring** Framework: inversion of control, dependency injection, etc.
* Packaging as a single war with embedded container (tomcat 8): No need to install a container separately on the host just run using the ``java -jar`` command
* Demonstrates how to set up healthcheck, metrics, info, environment, etc. endpoints automatically on a configured port. Inject your own health / metrics info with a few lines of code.
* Writing a RESTful service using annotation: supports both XML and JSON request / response; simply use desired ``Accept`` header in your request
* Exception mapping from application exceptions to the right HTTP response with exception details in the body
* *Spring Data* Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
* Automatic CRUD functionality against the data source using Spring *Repository* pattern
* Demonstrates MockMVC test framework with associated libraries
* All APIs are "self-documented" by Swagger2 using annotations

Here are some endpoints you can call:

### Get information about system health, configurations, etc.

```
http://localhost:8080/
```

### Create a hotel resource

```
POST /example/v1/hotels
Accept: application/json
Content-Type: application/json

{
"name" : "Beds R Us",
"description" : "Very basic, small rooms but clean",
"city" : "Santa Ana",
"rating" : 2
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:8090/example/v1/hotels/1
```

### Retrieve a paginated list of hotels

```
http://localhost:8090/example/v1/hotels?page=0&size=10

Response: HTTP 200
Content: paginated list 
```





# Running the project with MySQL

This project uses an in-memory database so that you don't have to install a database in order to run it. However, converting it to run with another relational database such as MySQL or PostgreSQL is very easy. Since the project uses Spring Data and the Repository pattern, it's even fairly easy to back the same service with MongoDB.

Here is what you would do to back the services with MySQL, for example:

### In pom.xml add:

```
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

### Append this to the end of application.yml:

```
spring:
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
  datasource:
    url: jdbc:mysql://us-cdbr-east-06.cleardb.net:3306/heroku_6a8e7fa6e0bb8f0
    username: b3164ec35cc34e
    password: ac5d30be
    hikari:
      max-lifetime: 20000
    #url: jdbc:mysql://${MYSQL_HOST:localhost}:3306/${DB_NAME:PlusOne}
    #username: ${DB_USERNAME:root}
    #    password: ${DB_PASSWORD:JasonKim891214}
    #password: ${DB_PASSWORD:password}

    driver-class-name: com.mysql.cj.jdbc.Driver
  servlet:
    multipart:
      max-request-size: 50MB
      max-file-size: 40MB
```

### An authorized frontend is a React application, the repo's url is here (For more info about the frontend please also refer to the URL):
```
https://github.com/runninginair/Plus1-APP
```


## Postman test RESTFul APIs

**GET** get activities by user ID & date
```agsl
https://plusone-backend.herokuapp.com/activities/1/date/2014-04-01
```

**GET** get distance by time range
```agsl
https://plusone-backend.herokuapp.com/activity/getDistanceByTimeRange/1/2015-01-01/2015-01-31
```

**GET** get calories by time range
```agsl
https://plusone-backend.herokuapp.com/activity/getCaloriesByTimeRange/1/2016-12-01/2016-12-08
```

**GET** get user by Username
```agsl
https://plusone-backend.herokuapp.com/getUserByUsername/Nancyn
```
Note: "get user by Username" -- Not implemented by the front-end, we believe it is a technical debt. Instead of returning a String type, the function is better to return a Boolean result.

**POST** creat new activity
```agsl
https://plusone-backend.herokuapp.com/activity/create/2/date/2023-04-10/name/Swimming
```

**DEL** delete user by Username
```agsl
https://plusone-backend.herokuapp.com/deleteUserByUserName/ElleW
```

**PUT** update height by user id
```agsl
https://plusone-backend.herokuapp.com/updateHeightByUserID/4/76.5
```

**PUT** update weight by user id
```agsl
https://plusone-backend.herokuapp.com/updateWeightByUserID/4/99.99
```

## Deployed frontend and backend urls
```
https://plusone-frontend.herokuapp.com/home
https://plusone-backend.herokuapp.com/
```


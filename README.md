# Lottery Service
This is a Java based Lottery Service which consists an api set for managing Many online Lottery and Participants.


#### Used Frameworks and Technologies:
Spring Boot - Spring Web - Spring Data - Spring Security - Hibernate - JWT - Gradle - Swagger


#### Database:
mySql


#### User Interface :
Swagger-UI (http://yourWebsitePath:8081/swagger-ui.html)

#### Layers:
Api, Service, Domain

## Build and Run Project
In order to start the project please follow the Instructions below :
1- install mySql Service then set the connection settings in application.properties file in Resource directory. (I have used mySql Version 8.0.27.1)
2- Open the Project in IntelliJ Idea (Or any other IDE) this project is developed in IntelliJIdea
3- Build the source code via Gradle
4- Run the project By creating a Spring Boot-Run configuration
5- Use this address to see Swagger UI: (http://yourWebsitePath:8081/swagger-ui.html)

#### Hope you use and enjoy it.

## Outstanding Features and Interesting Specifications in Implementation:
there are many good Ideas that have made this project extensible and reliable like:
1- Domain Driven Design 
2- Solid and Clean Code
3- 3 Layer Structure working with a Core Domain
4- Comprehensive Backend: Restfull Api set for Managing Lotteries, User (Participants), Ballots and Winning Ballots
5- RealTime Draw: Executor Service runs the many draws at every midnight parallelly (MultiThreading approach)
6- Optimised Winning-Ballot Generator. Furthermore, Winning Ballots are Random, Unique and completely based on lottery specifications.
7- Robust Security Layer by using Spring Security and JWT
8- Swagger Ui for testing the APIs easily 
#### Feel free to ask any question regarding running.
#### Arman.heydarian@gmail.com

Best Regards.

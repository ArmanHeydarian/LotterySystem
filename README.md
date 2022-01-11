# Lottery System
This is a Java based Lottery System which consists an API set for running and managing Lotteries and Participants online.


#### Used Frameworks and Technologies:
Spring Boot - Spring Web - Spring Data - Spring Security - Hibernate - JWT - Gradle - Swagger - Log4J2


#### Database:
mySql


#### User Interface :
Swagger-UI (http://yourWebsitePath:8081/swagger-ui.html)

#### Layers:
API , Service (BL), Repository (DL), Domain (Model) and Security

## Build and Run Project
In order to start the project please follow the Instructions below :\
1- install mySql Service and create database with name: lotterydb then set the connection settings in application.properties file in Resource directory. (I have used mySql Version 8.0.27.1)\
2- Open the Project in IntelliJ Idea (Or any other IDE) this project is developed in IntelliJIdea\
3- Build the source code via Gradle\
4- Run the project By creating a Spring Boot-Run configuration\
5- Use this address to see Swagger UI: (http://yourWebsitePath:8081/swagger-ui.html) \
6- APIs like Lottery and User Management requires you to be Authorized as ADMIN or admin Role\
7- APIs like Participate requires you to be Authorized as user Role\
8- APIs like GetAllLotteries, GetWinningBallots, SignUp and SignIn are open for any user\ 
9- please notice that api GetWinningBallots requires standard date format like yyyy-MM-dd 

## Outstanding Features and Interesting Specifications in Implementation:
there are many good Ideas that have made this project extensible and reliable like:
#### 1- Domain Driven Design 
#### 2- Solid and Clean Code
#### 3- Multi Layers Structure working around a Core Domain
#### 4- Comprehensive Backend: Restfull Api set for Managing Lotteries, User (Participants), Ballots and Winning Ballots
#### 5- RealTime Draw: Executor Service runs the many draws at every midnight parallel (MultiThreading approach)
#### 6- Optimised Winning-Ballot Generator for generating Random, Unique WinningBallots regarding on lottery specifications.
#### 7- Robust Security Layer by using Spring Security and JWT
#### 8- Swagger Ui for testing the APIs easily 

* note: To get an overview of the structure and architecture please have a look at the Design folder content inside Zip file.

### Hope it will be satisfying :)
#### Feel free to ask any question regarding running.
#### Arman.heydarian@gmail.com

Best Regards.

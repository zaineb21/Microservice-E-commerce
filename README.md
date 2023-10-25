




## SpringBoot (Java) Backend micro services application
Application to handle the distributed web services in a single layout. 


### Technology Stack
Component         | Technology
---               | ---
Backend (REST)    | [SpringBoot](https://projects.spring.io/spring-boot) (Java)
Security          | Token Based ([JWT](https://github.com/auth0/java-jwt) )
REST Documentation| [Swagger UI / Springfox](https://github.com/springfox/springfox) and Postman
In Memory DB      | H2 , [MySql](https://www.mysql.com/fr/) and [MongoDB](https://www.mongodb.com/fr-fr)
Persistence       | JPA (Using Spring Data)
Server Build Tools| Maven(Java) 

## Folder Structure
```bash
PROJECT_FOLDER

└──[src]      
│  └──[main]      
│     └──[java]      

│        │  [controllers] #contains MS controllers
│        │  [entities] #contains MS entities
│        │  [repository] #contains MS repositories
│        │  [services] #contains  MS services

│     └──[resources]
│        │  application.properties #contains springboot cofigurations
│
└──[target]              #Java build files, auto-created after running java build: mvn install
│  └──[classes]
│     └──[public]
│     └──[webui]         #webui folder is created by (maven/gradle) which copies webui/dist folder 
│  Dockerfile
│  docker-compose.yaml
│  mvnw.cmd
│  pom.xml           
│  build.gradle
```

## Prerequisites
Ensure you have this installed before proceeding further
- Java 8
- Maven 4.0.0+
- Node 16.17.0,  
- npm 5 or above,   

## About
An e-commerce application treated with microservices is a modern, highly modular, and scalable online retail platform that utilizes a microservices architecture to manage various components of the application. In this architecture, individual functions such as product management, commands, deilvery and reclamations are broken down into separate, independent microservices. These microservices work together seamlessly to create a cohesive and agile e-commerce application. This approach enhances flexibility, resilience, and the ability to scale different parts of the application independently, ultimately providing a more responsive and efficient shopping experience for customers while simplifying maintenance and development for the platform's operators.
- Highlight techniques of making and securing a REST full app using [SpringBoot](https://projects.spring.io/spring-boot)

### Features of the Project
* Backend
  * Token Based Security (JWT)
  * API documentation links with Postman 
  * In Memory DB with H2 ,MySql and mongoDB
  * Using JPA and JDBC template to talk to relational database
  * How to request and respond for paginated data 

* Build
  * How to build all in one app that includes (database, sample data, RESTfull API, Auto generated API Docs and security)
  * Portable app, Ideal for dockers.

## In Memory DB (H2)
We had included an in-memory database for the application. Database schema and sample data for the app is created everytime the app starts, and gets destroyed after the app stops, so the changes made to to the database are persistent only as long as the app is running
<br/>

## JWT security

A JSON Web Token (JWT) is a secure way to represent claims between two parties. It is a signed token that contains a set of claims, such as the user's identity, roles, and permissions. JWTs are typically used to authenticate users and authorize them to access protected resources.

## OpenFeign

OpenFeign is a declarative REST client that makes it easy to consume HTTP APIs. It is a Spring Boot starter that provides a simple way to create REST clients for web services.

## Docker

Docker is a platform that simplifies the creation and deployment of applications by packaging them into lightweight, portable containers. These containers run consistently across different environments, making it easier to manage and scale applications.

## Docker compose

Docker Compose is a tool for defining and running multi-container Docker applications. It allows you to define an application's services, networks, and volumes in a single configuration file, making it easy to manage complex, multi-container applications as a single unit. Docker Compose simplifies the process of defining, orchestrating, and scaling applications composed of multiple containers.

### Accessing Application
Cpmponent         | URL                                      | 
---               | ---                                      |
H2 Database       |  http://localhost:9119/h2-console        |  
Swagger (API Ref) |  http://localhost:9119/swagger-ui.html   | 
Redoc (API Ref)   |  http://localhost:9119/redoc/index.html  |
Swagger Spec      |  http://localhost:9119/api-docs          |



**To get an authentication token** 



### Screenshots
#### System
![Dashboard](/screenshots/dockerSys.png?raw=true)

# Micro-Services-E-Commerce
### Getting Started with Micro Service Architecture :
In this application we have a total of 7 microServices :
| MicroService | Description |
| ------------ | ----------- |
| Api-Gateway  | API management tool that sits between a client and a collection of backend services|
| Eureka-Server-MS | This micro service is Our discovery Server  |
| AuthentificationMSGroupe | This micro service is designed to manage users and implement Authentication and Authorisation |
| dorraMS | This micro service is designed to manage commands |
| produit-ms | This micro service is designed to manage the products | 
| Reclamationservice | This micro service is designed to manage all the reclamations |
| SoulaymenfrMS | This micro service is designed to manage all the deliveries | 
 

### MSs & their PORTS
| MicroService | local PORT |
| ------------ | ----------- |
| Api-Gateway | 7070 |
| Eureka-Server | 8761 |
| AuthentificationMSGroupe | 8060 |
| dorraMS | 9090 |
| produit-ms | 8089 |
| Reclamationservice | 8040 |
| SoulaymenfrMS | 8080 | 


### how to run this application :
1- download the code of this repo 
2- install all the dependencies 
3- run `maven clean install` to all the micro services 
4- go to the root folder then run `docker compose up`
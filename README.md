# Microservices_complete_architecture
Microservice Architecture using Eureka Discovery Server, Zuul Api Gateway, Hystrix Fault Tolerance, Spring Cloud Sleuth, RabbitMQ and Zipkin

Eureka Service Discovery Server (runs on port 8761)
And four microservices which are Eureka Clients

1. Movie Catalog Service (runs on port 8081)
2. Movie Ratings Data Service (runs on port 8082)
3. Movie Info Service (runs on port 8083)
4. Netflix Zuul Api Gateway server runs on port 8765

#additional services
5. Hystrix Dashboard(runs on port 8081)
6. Zipkin Server runs on 9411 port
7. RabbitMQ runs on port 15672

The request comes in the Zuul Api Gateway and it routes to Movie Catalog Service
Movie Catalog Service discovers 2 other microservices and communicates with them using RestTemplate
Fallback methods are added for both the Microservice calls using Hystrix

All 4 microservices add Spring Cloud Sleuth depedency for segment id,trace id,micro service name
All the microservices implement AQMP Messaging dependency to write the logs to RabbitMQ
Once logs are posted to RabbitMQ, Zipkin server pullups those logs and generates distributed tracing for the requests

Movie Info Service commuicates with an external service https://www.themoviedb.org/ using an API key in RestTemplate

<img width="500" alt="API Architecture" src="https://github.com/dineschandgr/Microservices_complete_architecture/blob/master/architecture_diagram.jpg">


Requirements :

#add pom.xml file You need to add pom.xml with the maven dependencies

#Create AWS RDS MySQL DB or a local MYSQL DB

#To Create a RDS MYSQL Database

Create an RDS mysql 1st database named MovieCatalogDB (only 1 DB can be created from AWS RDS Console per service)
Set the RDS DB to public accessibility to YES (just for testing purposes. in real projects its in a private VPC and can be used only from a authorized VPC)
Copy the DB endpoint url, user name and pwd
Open the Port 3306 for your local machine IP
#Use the DB tool Sqlectron

Connect to the first mysql DB MovieCatalogDB creating from AWS RDS console. Create a table named movie_catalogue and insert sample data
Create a 2nd database named MovieInfoDB. Create a table named movie_info and insert sample data
Create a 3rd database named RatingsDataDB. Create a table named rating_info and insert sample data

#Update the DB credentials and Sever Port in application.properties file for every microservices project

#include management.endpoints.web.exposure.include=hystrix.stream for hystrix

#Install Erlang and RabbitMQ

#set Zipkin to listen to RabbitMQ
C:\>SET RABBIT_URI=amqp://localhost  
C:\> java -jar zipkin-server-2.12.9-exec.jar  

#Download Zipkin jar and run java -jar zipkin-server-2.12.9-exec.jar  

#Create an api key from themoviedb site and include in application.properties of Movie Info Service

#Route requests through zuul api gateway
zuul.routes.movie.url=http://localhost:8081  
zuul.routes.ratings.url=http://localhost:8083  

1. Start the Discovery Application Server Application first which runs on port 8761
2. Runs all three microservices which are registered which the Eureka Server
3. Access http://localhost:8761 to see Eureka Discovery Server Dashboard and verify that all 3 microservices are up and registered
4. Access http://localhost:8081/hystrix to view the Hystrix Dashboard
5. Access http://localhost:8081/catalog/user_name to access Movie Catalog Microservice
6. Access http://localhost:8082/movies/movie_id to access Movie Info Service
7. Access http://localhost:8083/ratingsdata/user/user_name to access Ratings Data Service
8. Microservice running on port 8081 consumes the services running on port 8082 and port 8083
9. Access http://localhost:8081/movie/catalog/user_name  to route request through zuul api gateway to movie catalog service 
10. Access http://localhost:15672 to view RabbitMQ console
11. Access http://localhost:9411/zipkin/ to view Zipkin console



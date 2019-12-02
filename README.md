# EMPIRE - simplE foruM aPplication In micRoservicEs



EMPIRE is an example of how to use microservices with Spring Boot for a develop a Forum application. The EMPIRE can be used as the base for various other projects with microservices. The software Eureka and Zull are used for service discovery and gateway. A Backend is created to the connection among all microservices. The view microservice is an application to the view layer. For the security of system, the JWT is used for generating a token to access the microservices. This project was developed based on the course of DevDojo.


## How to execute the project

1. Download the project with the clone in the repository or .zip

2. Open the project "forum-microservices" as a Maven project in an IDE

3. Install the lombok plugin in your IDE

4. Execute the Discovery microservice as a Java Application

5. Execute the Gateway microservice as a Java Application

6. Execute the others microservices also as a Java application

7. Open a browser and enter in localhost:8085



## How to introduce a new microservice in the project

1. Generate a new spring project and add in "forum-application"

2. In the pom.xml, change the parent to the main path (see the others pom.xml for example), change the build for the build defined in other microservices and remove the spring dependencies.

3. Rename the application.properties to application.yml and add the service discovery and the name of microservice (see the others microservices)



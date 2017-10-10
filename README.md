# Characters APIS

The API uses Spring Boot as the core development framework and Maven for project's build.

Please install the maven (https://maven.apache.org/install.html) to build the project with the following command:

* mvn clean install -Dmarvel.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.google.translator.key=AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc

Run one of the following commands to execute in debug or detached mode:

* mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" -Dmarvel.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.google.translator.key=AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc
* mvn spring-boot:run  -Dmarvel.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.google.translator.key=AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc


# Swagger

After running the app import the endpoints in the Postman or access the web page with the following links:

* http://localhost:8080/v2/api-docs
* http://localhost:8080/swagger-ui.html





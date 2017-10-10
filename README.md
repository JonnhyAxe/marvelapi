#Characters APIS

The API uses Spring boot as the core development framework and maven to install and run.

Please install the maven and run one of the following commands to execute in Debug or Detached

* mvn spring-boot:run -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005" -Dmarvel.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.google.translator.key=AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc


* mvn clean install -Dmarvel.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.apikey=746d7e48cbc8d37cf4dd63c53296c3c1 -Dmarvel.api.google.translator.key=AIzaSyDOVJKECU1EJeJvlIVFum93bCDgy3nmPCc


#Swagger

After running the app import the endpoint in the Postman or access the web page with the following links:

* http://localhost:8080/v2/api-docs
* http://localhost:8080/swagger-ui.html





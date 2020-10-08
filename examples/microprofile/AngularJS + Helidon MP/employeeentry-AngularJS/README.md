## AngularJS UI for sending POST requests to the Helidon MP backend developed in the project helidonH2Customized
## Ensure that the Helidon MP application is up and running

## Clean and Build the project
mvn clean package

## Execute the project
java -jar target\creditscore-se.jar

## Access the Web UI
http://localhost:8080
Enter values for Id, Name and Role and Submit.  This will send a POST request to the Helidon MP backend developed in the project helidonH2Customized and insert into the H2 database.

## Test if record inserted successfully
## GET request in the 


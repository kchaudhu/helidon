## AngularJS UI for sending POST requests to the Helidon MP backend developed in the project helidonH2Customized
## Ensure that the Helidon MP application is up and running
java -jar target/database-mp.jar

## Clean and Build the project
mvn clean package

## Execute the project
java -jar target\creditscore-se.jar

## Access the Web UI
http://localhost:8080

Enter values for Id, Name and Role and Submit.  This will send a POST request to the Helidon MP backend developed in the project helidonH2Customized and insert into the H2 database.

For example, enter the following values in UI :-

Id :- 21, Name :- Victor Sanchez, Role :- Vice President

## Test if record inserted successfully
## GET request in the Helidon MP application to test whether new record was inserted successfully.

For example, 
http://localhost:8081/employees/name/Victor%20Sanchez



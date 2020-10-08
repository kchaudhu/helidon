# Helidon MP Database
Helidon MP application that uses JPA with an in-memory H2 database

## Build and run
mvn clean package

java -jar target/database-mp.jar


## Execute the application 
Using curl, PostMan or any other REST client. There is an AngularJS project employeeentry-AngularJS that has a UI to send POST requests
## GET 
http://localhost:8081/employees/
http://localhost:8081/employees/count
http://localhost:8081/employees/{id}
http://localhost:8081/employees/name/{name}
## DELETE 
http://localhost:8081/employees/{id}
## POST 
Pass a JSON payload for id, name and role. There is also an AngularJS based project employeeentry-AngularJS that has a UI to send POST requests
http://localhost:8081/employees


## Try health and metrics
## GET
http://localhost:8081/health

# Prometheus Format
## GET
http://localhost:8081/metrics

## Build a Docker Image
docker build -t database-mp .

## Run docker Image
docker run --rm -p 8081:8081 database-mp:latest

Try the application as before.

## Deploy the application to Kubernetes
## Verify connectivity to cluster
kubectl cluster-info
kubectl get nodes

## Deploy the application to Kubernetes
kubectl create -f app.yaml
kubectl get pods                    # Wait for quickstart pod to be RUNNING

## The step above created a service that is exposed into any node port. Lookup the service to find the port.
## Lookup the service
kubectl get service database-mp

## Note the PORTs. You can now exercise the application as you did before but use the second port number (the NodePort) instead of 8081. For example:
## GET
http://localhost:32602/employees

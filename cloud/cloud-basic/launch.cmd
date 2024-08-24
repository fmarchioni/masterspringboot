@echo off

start cmd /k java -jar eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar

timeout /t 5

start cmd /k java -jar card-service\target\card-service-0.0.1-SNAPSHOT.jar

timeout /t 5

start cmd /k java -jar user-service\target\user-service-0.0.1-SNAPSHOT.jar

timeout /t 5


@echo off

start cmd /k java -jar eureka-server\target\eureka-server-0.0.1-SNAPSHOT.jar

timeout /t 5

start cmd /k java -jar card-service\target\card-service-0.0.1-SNAPSHOT.jar

timeout /t 5

start cmd /k java -jar user-service\target\user-service-0.0.1-SNAPSHOT.jar

timeout /t 5

rem start cmd /k java -jar user-service2\target\user-service2-0.0.1-SNAPSHOT.jar

rem timeout /t 5

start cmd /k java -jar gateway-proxy\target\gateway-proxy-0.0.1-SNAPSHOT.jar

timeout /t 30
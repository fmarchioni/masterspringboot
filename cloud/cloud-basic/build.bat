start cmd /k mvn --file eureka-server/pom.xml clean install -DskipTests=true spring-boot:run

timeout /t 2

start cmd /k mvn --file card-service/pom.xml clean install -DskipTests=true spring-boot:run

timeout /t 2

start cmd /k mvn --file user-service/pom.xml clean install -DskipTests=true spring-boot:run

 
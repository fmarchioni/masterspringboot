# Spring Cloud example application

This branch contains the demo with Spring Cloud Netflix Eureka. To see the same demo with other service registries, see the other branches.

After running all the apps execute POST at `localhost:9080/application` passing 
`cardApplication.json` as body.


- new card applications registered via `card-service`
- user registered via `user-service`
- `fraud-service` called by `card-service` and `user-service` to verify 
card applications and new users



# Setup using new stack

1. Add Eureka Server dependency:
```xml
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
   </dependency>
```
2. Enable the Eureka Server:

```
@EnableEurekaServer
```

3. Add Eureka Client dependencies in Services:

```xml
   	<dependency>
   		<groupId>org.springframework.cloud</groupId>
   		<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
   	</dependency>
```


4. Add EnableDiscoveryClient to Clients:

```java 
@EnableDiscoveryClient
```

5. Invoke the user-service microservice using the Discovery Service:

UserServiceClient.java
```java 
   	ServiceInstance instance = discoveryClient.getInstances("user-service")
   			.stream().findAny()
   			.orElseThrow(() -> new IllegalStateException("user-service unavailable"));

   	System.out.println("Chiamo MicroServizio UserService "+instance.getUri().toString()
   			+ "/registration");
   	return restTemplate.postForEntity(instance.getUri().toString()
   					+ "/registration",
   			userDto,
   			User.class);
``` 

6. Launch

```bash
launch.cmd
```

7. Check Eureka Server

http://localhost:8761

8. Test

```bash
curl -X POST -H "Content-Type: application/json" http://localhost:9080/application -d @cardApplication.json 
```


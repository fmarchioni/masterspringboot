# Sample Credit Card application eco-system

This branch contains the demo with Spring Cloud Netflix Eureka. To see the same demo with other service registries, see the other branches.

After running all the apps execute POST at `localhost:9080/application` passing 
`cardApplication.json` as body.

```bash
http POST localhost:9080/application < cardApplication.json
```

- new card applications registered via `card-service`
- user registered via `user-service`
- `fraud-service` called by `card-service` and `user-service` to verify 
card applications and new users


```
+-------+                         +-------------+       +-------------+          +-------+             +---------------+  +-------+
| User  |                         | CardService |       | UserService |          | Proxy |             | FraudVerifier |  | Proxy |
+-------+                         +-------------+       +-------------+          +-------+             +---------------+  +-------+
    |                                    |                     |                     |                         |              |
    | Register application               |                     |                     |                         |              |
    |----------------------------------->|                     |                     |                         |              |
    |                                    |                     |                     |                         |              |
    |                                    | Create new user     |                     |                         |              |
    |                                    |------------------------------------------>|                         |              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |     Create new user |                         |              |
    |                                    |                     |<--------------------|                         |              |
    |                                    |                     |                     |                         |              |
    |                                    |                     | Verify new user     |                         |              |
    |                                    |                     |-------------------->|                         |              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |                     | Verify new user         |              |
    |                                    |                     |                     |------------------------>|              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |                     |           User verified |              |
    |                                    |                     |                     |<------------------------|              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |       User verified |                         |              |
    |                                    |                     |<--------------------|                         |              |
    |                                    |                     |                     |                         |              |
    |                                    |        User created |                     |                         |              |
    |                                    |<--------------------|                     |                         |              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |                     |                         | User created |
    |                                    |<-----------------------------------------------------------------------------------|
    |                                    |                     |                     |                         |              |
    |                                    | Verify card application                   |                         |              |
    |                                    |-------------------------------------------------------------------->|              |
    |                                    |                     |                     |                         |              |
    |                                    |                     |                     Card application verified |              |
    |                                    |<--------------------------------------------------------------------|              |
    |                                    |                     |                     |                         |              |
    |        Card application registered |                     |                     |                         |              |
    |<-----------------------------------|                     |                     |                         |              |
    |                                    |                     |                     |                         |              |
```


# Card Service with Proxy Gateway

## Verify Dependency

```xml
   	<dependency>
   		<groupId>org.springframework.cloud</groupId>
   		<artifactId>spring-cloud-starter-gateway</artifactId>
   	</dependency> 
```  

## Configure Route Locator

```java
// ProxyConfig.java
@Bean
RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
    .route("users_service_route",
    route -> route.path("/user-service/**")
    .and()
    .method(HttpMethod.POST)
    .filters(filter -> filter.stripPrefix(1)
    )
    .uri("lb://user-service")).build();
}
```

## Use Proxy for User Service

```java
// UserServiceClient.java
		ServiceInstance instance = discoveryClient.getInstances("proxy")
				.stream().findAny()
				.orElseThrow(() -> new IllegalStateException("Proxy unavailable"));

		System.out.println("Chiamo MicroServizio UserService "+instance.getUri().toString()
				+ "/user-service/registration");
		return restTemplate.postForEntity(instance.getUri().toString()
						+ "/user-service/registration",
				userDto,
				User.class);
```
## Check Eureka Server

http://localhost:8761

## Test

```bash
curl -X POST -H "Content-Type: application/json" http://localhost:9080/application -d @cardApplication.json 
```



## Clone user-service and check load balancing

## Replace with configuration


```yaml
  cloud:
    gateway:
      routes:
        - id: users_service_route
          uri: lb://user-service
          predicates:
            - Path=/user-service/**
            - Method=POST
          filters:
            - StripPrefix=1
```


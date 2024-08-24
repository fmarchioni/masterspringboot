package org.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}

@RestController
@RequestMapping("/registration")
class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;

    UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping
    ResponseEntity<User> registerUser(@RequestBody UserDto userDto,
                                      UriComponentsBuilder uriComponentsBuilder) {

        User user = userRegistrationService.registerUser(userDto);
        UriComponents uriComponents = uriComponentsBuilder.path("/users/{uuid}")
                .buildAndExpand(user.getUuid());

        return ResponseEntity.created(uriComponents.toUri()).body(user);
    }
}

@Service
class UserRegistrationService {

    User registerUser(UserDto userDto) {
        User user = new User(userDto);
        verifyUser(user);
        return user;
    }

    private void verifyUser(User user) {

        System.out.println("Check user age");
        if (user.getAge() > 18) {
            user.setStatus(User.Status.OK);
        } else {
            user.setStatus(User.Status.FRAUD);
        }


    }

}
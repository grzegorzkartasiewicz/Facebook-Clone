package com.grzegorzkartasiewicz.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/users")
class UserController {
    private static final Logger logger = LoggerFactory.getLogger(User.class);
    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<User>> readAllUsers(){
        logger.info("Exposing all users!");
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    ResponseEntity<User> createUser(User newUser){
        logger.info("Adding new user!");
        var result = repository.save(newUser);
        var uri = "users/"+result.getId();
        return ResponseEntity.created(URI.create(uri)).build();
    }

}

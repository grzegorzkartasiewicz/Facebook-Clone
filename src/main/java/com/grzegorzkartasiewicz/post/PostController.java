package com.grzegorzkartasiewicz.post;

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
@RequestMapping("/posts")
class PostController {
    private static final Logger logger = LoggerFactory.getLogger(Post.class);
    private final PostRepository repository;

    PostController(PostRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Post>> readAllPosts(){
        logger.info("Exposing all posts!");
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    ResponseEntity<Post> createPost(Post newPost){
        logger.info("Adding new post!");
        var result = repository.save(newPost);
        var uri = "posts/"+result.getId();
        return ResponseEntity.created(URI.create(uri)).build();
    }
}

package com.grzegorzkartasiewicz.comment;

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
@RequestMapping("/comments")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(Comment.class);
    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    ResponseEntity<List<Comment>> readAllComments(){
        logger.info("Exposing all comments!");
        return ResponseEntity.ok(repository.findAll());
    }
    @PostMapping
    ResponseEntity<Comment> creatComment(Comment newComment){
        logger.info("Adding new comment!");
        var result = repository.save(newComment);
        var uri = "comments/"+result.getId();
        return ResponseEntity.created(URI.create(uri)).build();
    }
}

package com.grzegorzkartasiewicz.comment;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository repository;

    public CommentService(CommentRepository repository) {
        this.repository = repository;
    }


    public Comment createComment(Comment newComment){
        return repository.save(newComment);
    }

    public void deleteComment(int commentId){
        repository.deleteById(commentId);
    }
}

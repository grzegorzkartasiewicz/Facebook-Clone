package com.grzegorzkartasiewicz.comment;



import java.util.List;
import java.util.Optional;

interface CommentRepository {
    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    Comment save(Comment entity);
}

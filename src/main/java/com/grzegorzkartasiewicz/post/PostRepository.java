package com.grzegorzkartasiewicz.post;



import java.util.List;
import java.util.Optional;

interface PostRepository {
    List<Post> findAll();

    Optional<Post> findById(Integer id);

    Post save(Post entity);
}

package com.grzegorzkartasiewicz.post;


import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    Optional<Post> findById(Integer id);

    Post save(Post entity);

    void deleteById(Integer integer);
}

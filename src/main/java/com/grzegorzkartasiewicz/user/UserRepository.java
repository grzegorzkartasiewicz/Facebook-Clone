package com.grzegorzkartasiewicz.user;


import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    List<User> findAllByGroupId(Integer groupId);

    Optional<User> findById(Integer id);

    User save(User entity);
}

package com.grzegorzkartasiewicz.user;


import java.util.List;
import java.util.Optional;

interface UserRepository {
    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User entity);
}

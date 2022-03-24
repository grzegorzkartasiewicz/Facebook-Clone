package com.grzegorzkartasiewicz.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SqlUserRepository extends UserRepository, JpaRepository<User,Integer> {

}

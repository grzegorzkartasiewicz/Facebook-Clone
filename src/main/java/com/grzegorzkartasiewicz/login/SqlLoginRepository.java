package com.grzegorzkartasiewicz.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface SqlLoginRepository extends LoginRepository, JpaRepository<Login,Integer> {

}

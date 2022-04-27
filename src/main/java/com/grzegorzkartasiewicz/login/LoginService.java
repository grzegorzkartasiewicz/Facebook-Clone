package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.User;
import com.grzegorzkartasiewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginRepository repository;
    private final UserRepository userRepository;

    LoginService(LoginRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    User signInUser(User user){
        return userRepository.save(user);
    }

    User logInUser(String login,String password){
        logger.info("Trying to log in user!");
       var loggedUser = repository.findAll().stream()
               .filter(login1 -> login1.getNick().equals(login))
               .filter(login1 -> login1.getPassword().equals(password))
               .findFirst();
        return loggedUser.map(Login::getUser).orElse(null);
    }

}

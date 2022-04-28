package com.grzegorzkartasiewicz.login;
/*
 * Spring controller made for logging and signing up users.
 *
 * @author Grzegorz Kartasiewicz
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
class LoginController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginRepository repository;
    private final LoginService service;

    LoginController(LoginRepository repository, LoginService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    String login(Model model){
        logger.info("Showing login template!");
        model.addAttribute("login",new Login());
        return "login";
    }
    @GetMapping("/logout")
    String logout(HttpSession session, Model model){
        logger.info("Logging out user!");
        session.invalidate();
        model.addAttribute("login",new Login());
        return "login";
    }
    @GetMapping("/error")
    String error(Model model){
        logger.info("Error has occurred!");
        model.addAttribute("login",new Login());
        return "login";
    }

    @PostMapping
    String logInUser(HttpSession session, Model model, String login, String password){
        logger.info("Logging in user!");
        var loggedUser = service.logInUser(login,password);
        if(loggedUser==null){
            model.addAttribute("login",new Login());
            model.addAttribute("message","Invalid login or password");
            return "login";
        }
        session.setAttribute("user", loggedUser);
        return "redirect:/posts/";
    }
    @PostMapping(path = "/sign")
    String signUpUser(HttpSession session, Model model, @ModelAttribute("login") @Valid Login newLogin, BindingResult bindingResult){
        logger.info("Signing up new user!");
        if(bindingResult.hasErrors()){
            model.addAttribute("login",new Login());
            return "login";
        }
        repository.save(newLogin);
        newLogin.setUser(service.signInUser(newLogin.getUser()));
        session.setAttribute("user", newLogin.getUser());
        return "redirect:/posts/";
    }
}

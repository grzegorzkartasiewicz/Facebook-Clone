package com.grzegorzkartasiewicz.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository repository;
    private final UserService service;

    UserController(UserRepository repository, UserService service) {
        this.repository = repository;
        this.service = service;
    }


    @GetMapping
    String showUser(Model model, @RequestParam int user){
        logger.info("Showing chosen user!");
        var userToShow = repository.findById(user);
        if(userToShow.isPresent()){
            model.addAttribute("user",userToShow.get());
        } else{
            model.addAttribute("message","User not found");
        }
        return "user";

    }
    @PostMapping
    String addUserPost(HttpSession session, Model model,
                        String description){
        logger.info("Creating new post!");
        var loggedUser = (User) session.getAttribute("user");
        service.createPost(loggedUser.getId(),description);
        model.addAttribute("user", loggedUser);
        return "user";
    }
    @PostMapping("/comments/{postId}")
    String addUserComment(HttpSession session,
            @ModelAttribute("users") User current, Model model,
                          @PathVariable int postId,
                          String description){
        logger.info("Creating new comment!");
        var loggedUser= (User) session.getAttribute("user");
        service.createComment(loggedUser,postId,description);
        model.addAttribute("user", current);
        return "user";
    }

    @PostMapping("/posts/delete/{postId}")
    String deleteUserPost(@ModelAttribute("users") User current, Model model,
                          @PathVariable int postId){
        logger.info("Deleting post!");
        service.deletePost(postId);
        model.addAttribute("user", current);
        return "user";
    }

    @PostMapping("/comment/delete/{commentId}")
    String deleteUserComment(@ModelAttribute("users") User current, Model model,
                          @PathVariable int commentId){
        logger.info("Deleting comment!");
        service.deleteComment(commentId);
        model.addAttribute("user", current);
        return "user";
    }
    @GetMapping("/home")
    String showUserProfile(@ModelAttribute("users") User current,Model model){
        logger.info("Showing profile of logged user!");
        model.addAttribute("user",current);
        return "user";

    }

    @ModelAttribute("users")
    List<User> getUsers(){
        return repository.findAll();
    }
}

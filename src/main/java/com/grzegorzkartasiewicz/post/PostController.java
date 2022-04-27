package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.user.User;
import com.grzegorzkartasiewicz.user.UserRepository;
import com.grzegorzkartasiewicz.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/posts")
class PostController {
    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostRepository repository;
    private final UserRepository userRepository;
    private final PostService service;
    private final UserService userService;

    PostController(PostRepository repository, UserRepository userRepository, PostService service, UserService userService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.service = service;
        this.userService = userService;
    }

    @GetMapping("/home")
    String showHomePage(Model model){
        logger.info("Showing home page!");
        var postToEdit = new Post();
        model.addAttribute("posts", getPosts());
        model.addAttribute("post", postToEdit);
        return "posts";
    }
    @GetMapping
    String showPosts(HttpSession session,Model model){
        logger.info("Showing home page for logged user!");
        var postToEdit = new Post();
        session.setAttribute("posts", getPosts());
        model.addAttribute("post", postToEdit);
        return "posts";

    }
    @PostMapping("/{id}")
    String addUserPost(Model model,
                       @PathVariable int id,
                       String description){
        logger.info("Creating new post!");
        userService.createPost(id,description);
        model.addAttribute("posts", getPosts());
        return "posts";
    }
    @PostMapping("/comments/{postId}")
    String addUserPostComment(
                        HttpSession session,
                       Model model,
                       @PathVariable int postId,
                       String description){
        logger.info("Creating new comment!");
        User user = (User) session.getAttribute("user");
        service.createComment(user, postId,description);
        model.addAttribute("posts", getPosts());
        return "posts";
    }
    @GetMapping("/search")
    String searchUsersAndPosts(Model model, @RequestParam String search){
        logger.info("Showing searched users and posts!");
        model.addAttribute("posts", service.searchPosts(search));
        model.addAttribute("users",userService.searchUsers(search));
        return "posts";
    }
    @PostMapping("/comment/delete/{commentId}")
    String deleteComment(HttpSession session,
                         Model model,
                         @PathVariable int commentId){
        logger.info("Deleting comment!");
        service.deleteComment(commentId);
        model.addAttribute("posts", getPosts());
        return "posts";
    }
    @PostMapping("/delete/{postId}")
    String deletePost(HttpSession session,
                         Model model,
                         @PathVariable int postId){
        logger.info("Deleting post!");
        service.deletePost(postId);
        model.addAttribute("posts", getPosts());
        return "posts";
    }

    @ModelAttribute("posts")
    List<Post> getPosts(){
        return repository.findAll();
    }
}

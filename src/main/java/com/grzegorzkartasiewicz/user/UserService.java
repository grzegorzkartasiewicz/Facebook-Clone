package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.post.Post;
import com.grzegorzkartasiewicz.post.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    public static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository repository;
    private PostService postService;

    public UserService(UserRepository repository, PostService postService) {
        this.repository = repository;
        this.postService = postService;
    }

    public Post createPost(int id, String description){
        logger.info("Creating post to save in DB!");
        return repository.findById(id).map(user -> {
            var targetPost = new Post();
            targetPost.setDescription(description);
            targetPost.setUser(user);
            return postService.createPost(targetPost);
        }).orElseThrow(() ->new IllegalArgumentException("User with given id was not found!"));
    }
    Comment createComment(User user, int postId, String description){
        return postService.createComment(user, postId, description);
    }
    void deletePost(int postId){
        postService.deletePost(postId);
    }
    void deleteComment(int commentId){
        postService.deleteComment(commentId);
    }

    public List<User> searchUsers(String search) {
        return repository.findAll().stream().filter(user -> user.getName().toLowerCase().contains(search.toLowerCase()) || user.getSurname().toLowerCase().contains(search.toLowerCase())).toList();
    }
}

package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.comment.CommentService;
import com.grzegorzkartasiewicz.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class PostService {
    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository repository;
    private final CommentService commentService;

    public PostService(PostRepository repository, CommentService commentService) {
        this.repository = repository;
        this.commentService = commentService;
    }

    public Post createPost(Post source){
        return repository.save(source);
    }
    public Comment createComment(User user, int postId,String description){
        logger.info("Creating comment to save in DB!");
        return repository.findById(postId).map(post -> {
            var targetComment = new Comment();
            targetComment.setDescription(description);
            targetComment.setPost(post);
            targetComment.setUser(user);
            return commentService.createComment(targetComment);
        }).orElseThrow(() ->new IllegalArgumentException("Post with given id was not found!"));
    }

    List<Post> searchPosts(String search) {
        logger.info("Searching for matching users and posts!");
        return repository.findAll().stream().filter(post -> post.getDescription().toLowerCase().contains(search.toLowerCase())).toList();
    }

    public void deleteComment(int commentId){
        commentService.deleteComment(commentId);
    }

    public void deletePost(int postId){
        repository.findById(postId).get().getComments().forEach(comment -> commentService.deleteComment(comment.getId()));
        repository.deleteById(postId);
    }
}

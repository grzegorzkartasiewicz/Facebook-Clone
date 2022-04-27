package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.comment.CommentRepository;
import com.grzegorzkartasiewicz.comment.CommentService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class PostServiceTest {

    @Test
    void createComment_returns_comment_with_given_description(){
        //given
        var post = new Post(0,"foo","bar",null,null);
        var mockPostRepository = mock(PostRepository.class);
        when(mockPostRepository.findById(anyInt())).thenReturn(Optional.of(post));
        var mockCommentService = new CommentService(dummyCommentService());
        //system under test
        var toTest = new PostService(mockPostRepository,mockCommentService);
        //when + then
        var comment = toTest.createComment(null,-1,"bar");
        assertThat(comment).hasFieldOrPropertyWithValue("description","bar");
    }
    @Test
    void createComment_returns_IllegalArgumentException(){
        //given
        var post = new Post(0,"foo","bar",null,null);
        var mockPostRepository = mock(PostRepository.class);
        when(mockPostRepository.findById(anyInt())).thenReturn(Optional.empty());
        var mockCommentService = new CommentService(dummyCommentService());
        //system under test
        var toTest = new PostService(mockPostRepository,mockCommentService);
        //when + then
        var exception = catchThrowable(() -> toTest.createComment(null,-1,"bar"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);
    }

    private CommentRepository dummyCommentService(){
        return new CommentRepository(){

            @Override
            public List<Comment> findAll() {
                return null;
            }

            @Override
            public Optional<Comment> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public Comment save(Comment entity) {
                return entity;
            }

            @Override
            public void deleteById(Integer integer) {

            }
        };
    }

}

package com.grzegorzkartasiewicz.user;


import com.grzegorzkartasiewicz.post.Post;
import com.grzegorzkartasiewicz.post.PostRepository;
import com.grzegorzkartasiewicz.post.PostService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Test
    void createPost_returns_post_with_given_description(){
        //given
        var user = new User(0,"foo","bar",0,null,null,null);
        var mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.of(user));
        var mockPostService = new PostService(dummyPostRepository(),null);
        //system under test
        var toTest = new UserService(mockUserRepository,mockPostService);
        //when + then
        var post = toTest.createPost(-1,"foo");
        assertThat(post).hasFieldOrPropertyWithValue("description","foo");
    }
    @Test
    void createPost_returns_IllegalArgumentException(){
        //given
        var user = new User(0,"foo","bar",0,null,null,null);
        var mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.findById(anyInt())).thenReturn(Optional.empty());
        var mockPostService = new PostService(dummyPostRepository(),null);
        //system under test
        var toTest = new UserService(mockUserRepository,mockPostService);
        //when + then
        var exception = catchThrowable(()-> toTest.createPost(-1,"foo"));
        assertThat(exception).isInstanceOf(IllegalArgumentException.class);

    }

    private PostRepository dummyPostRepository(){
        return new PostRepository() {
            @Override
            public List<Post> findAll() {
                return null;
            }

            @Override
            public Optional<Post> findById(Integer id) {
                return Optional.empty();
            }

            @Override
            public Post save(Post entity) {
                return entity;
            }

            @Override
            public void deleteById(Integer integer) {

            }
        };
    }
}

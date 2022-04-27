package com.grzegorzkartasiewicz.login;

import com.grzegorzkartasiewicz.user.User;
import com.grzegorzkartasiewicz.user.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

class LoginServiceTest {

    @Test
    void logInUser_returns_logged_user(){
        //given
        var user = new User(0,"foo","bar",0,null,null,null);
        var login = new Login(0,"foo","bar","foobar",user);
        List<Login> logins = new ArrayList<>();
        logins.add(login);
        var mockLoginRepository = mock(LoginRepository.class);
        when(mockLoginRepository.findAll()).thenReturn(logins);
        var mockUserRepository = mock(UserRepository.class);
        //system under test
        var toTest = new LoginService(mockLoginRepository,mockUserRepository);
        //when + then
        user = toTest.logInUser("foo","bar");
        assertThat(user).hasFieldOrPropertyWithValue("name","foo");
    }
    @Test
    void logInUser_returns_null(){
        //given
        var user = new User(0,"foo","bar",0,null,null,null);
        var login = new Login(0,"foo","bar","foobar",user);
        List<Login> logins = new ArrayList<>();
        logins.add(login);
        var mockLoginRepository = mock(LoginRepository.class);
        when(mockLoginRepository.findAll()).thenReturn(logins);
        var mockUserRepository = mock(UserRepository.class);
        //system under test
        var toTest = new LoginService(mockLoginRepository,mockUserRepository);
        //when + then
        user = toTest.logInUser("bar","foo");
        assertThat(user).isNull();
    }
}

package com.grzegorzkartasiewicz.login;


import com.grzegorzkartasiewicz.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "User must have login")
    private String nick;
    @NotBlank(message = "User must have password")
    private String password;
    @NotBlank(message = "User must have email")
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Login() {
    }

    public Login(int id, String nick, String password, String email, User user) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.email = email;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

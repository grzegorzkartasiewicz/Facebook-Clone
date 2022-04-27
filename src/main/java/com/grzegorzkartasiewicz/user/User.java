package com.grzegorzkartasiewicz.user;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.login.Login;
import com.grzegorzkartasiewicz.post.Post;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "User must have name")
    private String name;
    @NotBlank(message = "User must have surname")
    private String surname;
    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToOne(mappedBy = "user")
    private Login login;


    public User(){}

    public User(int id, String name, String surname, int age, List<Post> posts, List<Comment> comments, Login login) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.posts = posts;
        this.comments = comments;
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}

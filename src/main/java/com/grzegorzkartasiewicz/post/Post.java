package com.grzegorzkartasiewicz.post;

import com.grzegorzkartasiewicz.comment.Comment;
import com.grzegorzkartasiewicz.user.User;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String time;
    @NotBlank(message = "Description can not be empty!")
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    public Post() {
    }

    public Post(int id, String time, String description, User user, List<Comment> comments) {
        this.id = id;
        this.time = time;
        this.description = description;
        this.user = user;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    void prePersist(){
        time = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT,FormatStyle.SHORT));
    }
}

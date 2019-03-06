package com.codeup.blog.models;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="users")
public class User {

    @Id @GeneratedValue
    private int id;
    @Column(nullable = false, length = 100, unique = true)
    private String username;
    @Column(nullable=false, length = 150, unique = true)
    private String email;
    @Column(nullable=false, length = 150)
    private String password;
//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

    public User(){

    }

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(User copy) {
        id = copy.id;// This line is SUPER important! Many things won't work if it's absent
        username = copy.username;
        email = copy.email;
        password = copy.password;
        //profilePicture =copy.profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

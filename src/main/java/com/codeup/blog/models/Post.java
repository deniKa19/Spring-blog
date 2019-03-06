package com.codeup.blog.models;

import javax.persistence.*;
@Entity
@Table(name="posts")
public class Post {
    @Column(nullable = false, length = 200)
    private String title;
    @Column(nullable = false, length= 250)
    private String body;
    @Id @GeneratedValue
    private long id;
    @Column
    private long user_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public Post() {
    }


    public Post(String title, String body) {
        this.title = title;
        this.body = body;

    }

    public Post(String title, String body, long id) {
        this.title = title;
        this.body = body;
        this.id= id;

    }

    public Post(long user_id, String title, String body) {
        this.user_id= user_id;
        this.title = title;
        this.body = body;


    }

    public Post(String title, String body, long id, long user_id) {
        this.title = title;
        this.body = body;
        this.id = id;
        this.user_id= user_id;

    }


    public String getTitle() {
        return title;
        }

    public void setTitle (String title) {
        this.title = title;
        }

    public String getBody () {
            return body;
        }

    public void setBody (String body) {
            this.body = body;
        }

     public long getId() {
        return id;
     }

     public void setId(long id) {
        this.id= id;
     }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }
}

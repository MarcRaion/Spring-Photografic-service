package com.marcinha.stylist.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comments_id")
    private int id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id")
    private Post posts;

    @Column(name = "adding_date")
    private Date date;

    @Column
    private String text;


    public Comments() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Post getPosts() {
        return posts;
    }

    public void setPosts(Post posts) {
        this.posts = posts;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comments)) return false;

        Comments comments = (Comments) o;

        if (!getUsers().equals(comments.getUsers())) return false;
        if (!getPosts().equals(comments.getPosts())) return false;
        return getDate().equals(comments.getDate());
    }

    @Override
    public int hashCode() {
        int result = getUsers().hashCode();
        result = 31 * result + getPosts().hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }
}

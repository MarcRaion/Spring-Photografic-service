package com.marcinha.stylist.entity;

import javax.persistence.*;


import java.util.Date;
import java.util.List;

@Entity
@Table(name="POST")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "post_next_id", allocationSize = 1)
    @Column(name ="post_id")
    private Long id;

    @Column
    private String author;
    @Column
    private String title;
    @Column(name="adding_date")
    private Date addingDate;
    @Column
    private String description;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String image;

    @Column(name="count_likes")
    private int countLikes;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> userLikes;

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    private List<Comments> userComments;


    public Post() {
    }

    public Post(String author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddingDate() {
        int year = addingDate.getYear();
        int month = addingDate.getMonth();
        int day = addingDate.getDay();

        return year+1900+"."+month+"."+day;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<User> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<User> userLikes) {
        this.userLikes = userLikes;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public List<Comments> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comments> userComments) {
        this.userComments = userComments;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", addingDate=" + addingDate +
                ", description='" + description + '\'' +
                '}';
    }

}

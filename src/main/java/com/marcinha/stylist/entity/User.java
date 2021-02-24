package com.marcinha.stylist.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "user_next_id", allocationSize = 1)
    @Column(name ="user_id")
    private int id;

    @Column
    private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name="country_id")
    private int countryId;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.EAGER
    )
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id"))
    private List<Post> postLiked;

    @OneToMany(mappedBy = "users",cascade = CascadeType.ALL)
    private List<Comments> postComments;



    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password, int countryId) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.countryId = countryId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public List<Post> getPostLiked() {
        return postLiked;
    }

    public void setPostLiked(List<Post> postLiked) {
        this.postLiked = postLiked;
    }

    public List<User> getFriends() {
        for(int i =0; i<following.size(); i++){
            System.out.println(i);
        }

        return following;
    }

    public void follow(User user){
        if ( following == null){
            following = new ArrayList<>();
        }
        following.add(user);

    }

    public void setFriends(List<User> following) {
        this.following = following;
    }

    public List<Comments> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<Comments> postComments) {
        this.postComments = postComments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", countryId=" + countryId +
                '}';
    }
}

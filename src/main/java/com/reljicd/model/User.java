package com.reljicd.model;

import java.util.Collection;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @JsonIgnore
    private String password;

    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 5, message = "*Your username must have at least 5 characters")
    @NotEmpty(message = "*Please provide your username")
    private String username;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    @Column(name = "active", nullable = false)
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles = new java.util.HashSet<>(); // Initialized to avoid null issues

    @OneToMany(mappedBy = "user")
    private Collection<Post> posts = new java.util.ArrayList<>(); // Initialized to avoid null issues

    // Default constructor
    public User() {
    }

    // Constructor with parameters
    public User(String email, String password, String username, String name, String lastName, int active) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.active = active;
    }
}

// package com.reljicd.model;

// import com.fasterxml.jackson.annotation.JsonIgnore;
// import org.hibernate.validator.constraints.Email;
// import org.hibernate.validator.constraints.Length;
// import org.hibernate.validator.constraints.NotEmpty;

// import javax.persistence.*;
// import java.util.Collection;

// @Entity
// @Table(name = "user")
// public class User {

// @Id
// @GeneratedValue(strategy = GenerationType.AUTO)
// @Column(name = "user_id")
// private Long id;

// @Column(name = "email", unique = true, nullable = false)
// @Email(message = "*Please provide a valid Email")
// @NotEmpty(message = "*Please provide an email")
// private String email;

// @Column(name = "password", nullable = false)
// @Length(min = 5, message = "*Your password must have at least 5 characters")
// @NotEmpty(message = "*Please provide your password")
// @JsonIgnore
// private String password;

// @Column(name = "username", nullable = false, unique = true)
// @Length(min = 5, message = "*Your username must have at least 5 characters")
// @NotEmpty(message = "*Please provide your name")
// private String username;

// @Column(name = "name")
// @NotEmpty(message = "*Please provide your name")
// private String name;

// @Column(name = "last_name")
// @NotEmpty(message = "*Please provide your last name")
// private String lastName;

// @Column(name = "active", nullable = false)
// private int active;

// @ManyToMany(cascade = CascadeType.ALL)
// @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
// inverseJoinColumns = @JoinColumn(name = "role_id"))
// private Collection<Role> roles;

// @OneToMany(mappedBy = "user")
// private Collection<Post> posts;

// public Long getId() {
// return id;
// }

// public void setId(Long id) {
// this.id = id;
// }

// public String getPassword() {
// return password;
// }

// public void setPassword(String password) {
// this.password = password;
// }

// public String getUsername() {
// return username;
// }

// public void setUsername(String username) {
// this.username = username;
// }

// public String getName() {
// return name;
// }

// public void setName(String name) {
// this.name = name;
// }

// public String getLastName() {
// return lastName;
// }

// public void setLastName(String lastName) {
// this.lastName = lastName;
// }

// public String getEmail() {
// return email;
// }

// public void setEmail(String email) {
// this.email = email;
// }

// public int getActive() {
// return active;
// }

// public void setActive(int active) {
// this.active = active;
// }

// public Collection<Role> getRoles() {
// return roles;
// }

// public void setRoles(Collection<Role> roles) {
// this.roles = roles;
// }

// public Collection<Post> getPosts() {
// return posts;
// }

// public void setPosts(Collection<Post> posts) {
// this.posts = posts;
// }
// }

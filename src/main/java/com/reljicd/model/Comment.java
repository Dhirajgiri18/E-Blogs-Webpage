package com.reljicd.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "body", columnDefinition = "TEXT")
    @NotEmpty(message = "*Please write something")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;

}


// package com.reljicd.model;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.validator.constraints.NotEmpty;

// import javax.persistence.*;
// import javax.validation.constraints.NotNull;
// import java.util.Date;

// @Entity
// @Table(name = "comment")
// public class Comment {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "comment_id")
//     private Long id;

//     @Column(name = "body", columnDefinition = "TEXT")
//     @NotEmpty(message = "*Please write something")
//     private String body;

//     @Temporal(TemporalType.TIMESTAMP)
//     @Column(name = "create_date", nullable = false, updatable = false)
//     @CreationTimestamp
//     private Date createDate;

//     @ManyToOne
//     @JoinColumn(name = "post_id", referencedColumnName = "post_id", nullable = false)
//     @NotNull
//     private Post post;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//     @NotNull
//     private User user;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getBody() {
//         return body;
//     }

//     public void setBody(String body) {
//         this.body = body;
//     }

//     public Date getCreateDate() {
//         return createDate;
//     }

//     public void setCreateDate(Date date) {
//         this.createDate = date;
//     }

//     public Post getPost() {
//         return post;
//     }

//     public void setPost(Post post) {
//         this.post = post;
//     }

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }
// }

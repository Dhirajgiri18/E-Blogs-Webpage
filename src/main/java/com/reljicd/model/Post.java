package com.reljicd.model;

import java.util.Collection;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "title", nullable = false)
    @Size(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy = "post", cascade = jakarta.persistence.CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Collection<Comment> comments;

    // No-argument constructor for Lombok
    //public Post() {}
}



// package com.reljicd.model;

// import org.hibernate.annotations.CreationTimestamp;
// import org.hibernate.validator.constraints.Length;
// import org.hibernate.validator.constraints.NotEmpty;

// import javax.persistence.*;
// import javax.validation.constraints.NotNull;
// import java.util.Collection;
// import java.util.Date;

// @Entity
// @Table(name = "post")
// public class Post {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "post_id")
//     private Long id;

//     @Column(name = "title", nullable = false)
//     @Length(min = 5, message = "*Your title must have at least 5 characters")
//     @NotEmpty(message = "*Please provide title")
//     private String title;

//     @Column(name = "body", columnDefinition = "TEXT")
//     private String body;

//     @Temporal(TemporalType.TIMESTAMP)
//     @Column(name = "create_date", nullable = false, updatable = false)
//     @CreationTimestamp
//     private Date createDate;

//     @ManyToOne
//     @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
//     @NotNull
//     private User user;

//     @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
//     private Collection<Comment> comments;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getTitle() {
//         return title;
//     }

//     public void setTitle(String title) {
//         this.title = title;
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

//     public User getUser() {
//         return user;
//     }

//     public void setUser(User user) {
//         this.user = user;
//     }

//     public Collection<Comment> getComments() {
//         return comments;
//     }

//     public void setComments(Collection<Comment> comments) {
//         this.comments = comments;
//     }
// }

package com.reljicd.model;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;

    // Optional: Constructor
    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }
}


// package com.reljicd.model;

// import javax.persistence.*;
// import java.util.Collection;

// @Entity
// @Table(name = "role")
// public class Role {

//     @Id
//     @GeneratedValue(strategy = GenerationType.AUTO)
//     @Column(name = "role_id")
//     private Long id;

//     @Column(name = "role", unique = true)
//     private String role;

//     @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
//     private Collection<User> users;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     public Collection<User> getUsers() {
//         return users;
//     }

//     public void setUsers(Collection<User> users) {
//         this.users = users;
//     }
// }

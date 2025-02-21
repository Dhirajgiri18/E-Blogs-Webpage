package com.reljicd.repository;

import com.reljicd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(@Param("email") String email);

    Optional<User> findByUsername(@Param("username") String username);
    
    // Example method to find all active users
    List<User> findByActive(int active);
    
    // Example method to delete a user by username
    void deleteByUsername(@Param("username") String username);
}


  // package com.reljicd.repository;

    // import com.reljicd.model.User;
    // import org.springframework.data.jpa.repository.JpaRepository;
    // import org.springframework.data.repository.query.Param;

    // import java.util.Optional;

    // public interface UserRepository extends JpaRepository<User, Long> {
    //     Optional<User> findByEmail(@Param("email") String email);

    //     Optional<User> findByUsername(@Param("username") String username);
    // }

package com.reljicd.service;

import com.reljicd.model.User;

import java.util.Optional;

/**
 * Service interface for managing users.
 */
public interface UserService {

    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<User> findByUsername(String username);

    /**
     * Finds a user by their email.
     *
     * @param email the email to search for
     * @return an Optional containing the user if found, or empty if not
     */
    Optional<User> findByEmail(String email);

    /**
     * Saves a new or existing user.
     *
     * @param user the user to save
     * @return the saved user
     */
    User save(User user);
}


// package com.reljicd.service;

// import com.reljicd.model.User;

// import java.util.Optional;

// public interface UserService {

//     Optional<User> findByUsername(String username);

//     Optional<User> findByEmail(String email);

//     User save(User user);
// }

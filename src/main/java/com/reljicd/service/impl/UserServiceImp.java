package com.reljicd.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reljicd.model.Role;
import com.reljicd.model.User;
import com.reljicd.repository.RoleRepository;
import com.reljicd.repository.UserRepository;
import com.reljicd.service.UserService;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String USER_ROLE = "ROLE_USER";

    @Autowired
    public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        // Check if user with the same email or username already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent() || userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("User with this email or username already exists.");
        }
        
        // Encode plaintext password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(1); // You can consider making this configurable

        // Retrieve role and handle potential null
        Role userRole = roleRepository.findByRole(USER_ROLE);
        if (userRole == null) {
            throw new IllegalStateException("User role not found.");
        }
        
        // Set Role to USER_ROLE
        user.setRoles(Collections.singleton(userRole)); // Using Set for roles

        return userRepository.saveAndFlush(user);
    }
}



// package com.reljicd.service.impl;

// import com.reljicd.model.User;
// import com.reljicd.repository.RoleRepository;
// import com.reljicd.repository.UserRepository;
// import com.reljicd.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import java.util.Collections;
// import java.util.Optional;

// @Service
// public class UserServiceImp implements UserService {

//     private final UserRepository userRepository;
//     private final RoleRepository roleRepository;
//     private final PasswordEncoder passwordEncoder;

//     private static final String USER_ROLE = "ROLE_USER";

//     @Autowired
//     public UserServiceImp(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//         this.userRepository = userRepository;
//         this.roleRepository = roleRepository;
//         this.passwordEncoder = passwordEncoder;
//     }

//     @Override
//     public Optional<User> findByUsername(String username) {
//         return userRepository.findByUsername(username);
//     }

//     @Override
//     public Optional<User> findByEmail(String email) {
//         return userRepository.findByEmail(email);
//     }

//     @Override
//     public User save(User user) {
//         // Encode plaintext password
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         user.setActive(1);
//         // Set Role to ROLE_USER
//         user.setRoles(Collections.singletonList(roleRepository.findByRole(USER_ROLE)));
//         return userRepository.saveAndFlush(user);
//     }
// }

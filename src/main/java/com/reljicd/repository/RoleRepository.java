package com.reljicd.repository;

import com.reljicd.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    // Find a role by its name
    Role findByRole(@Param("role") String role);
    
    // Additional method for deleting a role by its ID (if needed)
    void deleteById(Long id);
}



// package com.reljicd.repository;

// import com.reljicd.model.Role;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.query.Param;

// public interface RoleRepository extends JpaRepository<Role, Long> {
//     Role findByRole(@Param("role") String role);
// }

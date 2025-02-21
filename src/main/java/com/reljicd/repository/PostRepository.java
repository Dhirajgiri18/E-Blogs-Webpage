package com.reljicd.repository;

import com.reljicd.model.Post;
import com.reljicd.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);

    Optional<Post> findById(Long id);
    
    // Search posts by title
    Page<Post> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // Find posts created within a specific date range
    Page<Post> findByCreateDateBetween(Date startDate, Date endDate, Pageable pageable);
    
    // Delete posts by user
    void deleteByUser(User user);
}



// package com.reljicd.repository;

// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface PostRepository extends JpaRepository<Post, Long> {
//     Page<Post> findByUserOrderByCreateDateDesc(User user, Pageable pageable);

//     Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);

//     Optional<Post> findById(Long id);
// }

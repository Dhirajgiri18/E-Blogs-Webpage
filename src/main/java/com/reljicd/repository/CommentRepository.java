package com.reljicd.repository;

import com.reljicd.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find comments by post ID
    List<Comment> findByPostId(Long postId);

    // Find comments by user ID
    List<Comment> findByUserId(Long userId);

    // Custom query to find comments containing specific text
    @Query("SELECT c FROM Comment c WHERE c.body LIKE %:text%")
    List<Comment> findByBodyContaining(@Param("text") String text);
}



// package com.reljicd.repository;

// import com.reljicd.model.Comment;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface CommentRepository extends JpaRepository<Comment, Long> {
// }

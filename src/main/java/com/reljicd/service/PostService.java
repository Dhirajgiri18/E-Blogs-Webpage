package com.reljicd.service;

import com.reljicd.model.Post;
import com.reljicd.model.User;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PostService {

    Optional<Post> findById(Long id);

    Post save(Post post);

    Page<Post> findByUserOrderedByDatePageable(User user, int page);

    Page<Post> findAllOrderedByDatePageable(int page);

    void delete(Post post);
    
    // Optional: Method to update an existing post
    Post update(Long postId, Post updatedPost);
}


// package com.reljicd.service;

// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import org.springframework.data.domain.Page;

// import java.util.Optional;

// public interface PostService {

//     Optional<Post> findForId(Long id);

//     Post save(Post post);

//     /**
//      * Finds a {@link Page) of {@link Post} of provided user ordered by date
//      */
//     Page<Post> findByUserOrderedByDatePageable(User user, int page);

//     /**
//      * Finds a {@link Page) of all {@link Post} ordered by date
//      */
//     Page<Post> findAllOrderedByDatePageable(int page);

//     void delete(Post post);
// }


package com.reljicd.service;

import com.reljicd.model.Comment;

import java.util.Collection;

public interface CommentService {
    Comment save(Comment comment);
    Collection<Comment> findByPostId(Long postId);
    void delete(Long commentId);
}



// package com.reljicd.service;

// import com.reljicd.model.Comment;

// public interface CommentService {

//     Comment save(Comment comment);
// }

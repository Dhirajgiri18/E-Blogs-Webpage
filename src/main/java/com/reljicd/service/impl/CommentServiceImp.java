package com.reljicd.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reljicd.model.Comment;
import com.reljicd.repository.CommentRepository;
import com.reljicd.service.CommentService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImp(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Collection<Comment> findByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void delete(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    // Optional: Method to update a comment
    public Comment update(Long commentId, Comment updatedComment) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    comment.setBody(updatedComment.getBody());
                    return commentRepository.save(comment);
                })
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }
}



// package com.reljicd.service.impl;

// import com.reljicd.model.Comment;
// import com.reljicd.repository.CommentRepository;
// import com.reljicd.service.CommentService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class CommentServiceImp implements CommentService {

//     private final CommentRepository commentRepository;

//     @Autowired
//     public CommentServiceImp(CommentRepository commentRepository) {
//         this.commentRepository = commentRepository;
//     }

//     @Override
//     public Comment save(Comment comment) {
//         return commentRepository.saveAndFlush(comment);
//     }
// }

package com.reljicd.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.repository.PostRepository;
import com.reljicd.service.PostService;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
        return postRepository.findByUserOrderByCreateDateDesc(user, PageRequest.of(subtractPageByOne(page), 5));
    }

    @Override
    public Page<Post> findAllOrderedByDatePageable(int page) {
        return postRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page) {
        return Math.max(page - 1, 0);
    }

    @Override
    @Transactional
    public Post update(Long postId, Post updatedPost) {
        return postRepository.findById(postId).map(existingPost -> {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setBody(updatedPost.getBody());
            return postRepository.save(existingPost);
        }).orElseThrow(() -> new RuntimeException("Post not found with id: " + postId));
    }
}




// package com.reljicd.service.impl;

// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import com.reljicd.repository.PostRepository;
// import com.reljicd.service.PostService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.stereotype.Service;

// import java.util.Optional;

// @Service
// public class PostServiceImp implements PostService {

//     private final PostRepository postRepository;

//     @Autowired
//     public PostServiceImp(PostRepository postRepository) {
//         this.postRepository = postRepository;
//     }

//     @Override
//     public Optional<Post> findForId(Long id) {
//         return postRepository.findById(id);
//     }

//     @Override
//     public Post save(Post post) {
//         return postRepository.saveAndFlush(post);
//     }

//     @Override
//     public Page<Post> findByUserOrderedByDatePageable(User user, int page) {
//         return postRepository.findByUserOrderByCreateDateDesc(user, new PageRequest(subtractPageByOne(page), 5));
//     }

//     @Override
//     public Page<Post> findAllOrderedByDatePageable(int page) {
//         return postRepository.findAllByOrderByCreateDateDesc(new PageRequest(subtractPageByOne(page), 5));
//     }

//     @Override
//     public void delete(Post post) {
//         postRepository.delete(post);
//     }

//     private int subtractPageByOne(int page){
//         return (page < 1) ? 0 : page - 1;
//     }
// }

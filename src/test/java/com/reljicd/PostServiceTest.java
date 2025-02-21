package com.reljicd.blogdemo.service;

import com.reljicd.blogdemo.model.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    @Test
    public void testCreatePost() {
        Post post = new Post();
        post.setTitle("Test Post");
        post.setContent("This is a test post.");

        Post createdPost = postService.createPost(post);

        assertThat(createdPost).isNotNull();
        assertThat(createdPost.getId()).isNotNull(); // Assuming your service generates an ID
        assertThat(createdPost.getTitle()).isEqualTo("Test Post");
        assertThat(createdPost.getContent()).isEqualTo("This is a test post.");
    }
}

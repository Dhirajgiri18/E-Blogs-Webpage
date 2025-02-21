package com.reljicd.controller;

import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.service.PostService;
import com.reljicd.service.UserService;

import jakarta.validation.Valid; // Updated import

@Controller
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/newPost")
    public String newPost(Principal principal, Model model) {
        Optional<User> user = userService.findByUsername(principal.getName());
        if (user.isPresent()) {
            Post post = Post.builder().user(user.get()).build();
            model.addAttribute("post", post);
            return "postForm";
        } else {
            logger.warn("User not found: {}", principal.getName());
            return "error";
        }
    }

    @PostMapping("/newPost")
    public String createNewPost(@Valid Post post, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors while creating post: {}", bindingResult.getAllErrors());
            return "postForm";
        } else {
            postService.save(post);
            logger.info("Post created successfully: {}", post.getTitle());
            return "redirect:/post/" + post.getId(); // Redirect to the newly created post
        }
    }

    @GetMapping("/editPost/{id}")
    public String editPostWithId(@PathVariable Long id, Principal principal, Model model) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("post", post);
                return "postForm";
            } else {
                logger.warn("Access denied for user: {} to edit post: {}", principal.getName(), id);
                return "403";
            }
        } else {
            logger.error("Post not found for editing: id = {}", id);
            return "error";
        }
    }

    @GetMapping("/post/{id}")
    public String getPostWithId(@PathVariable Long id, Principal principal, Model model) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }
            return "post";
        } else {
            logger.error("Post not found: id = {}", id);
            return "error";
        }
    }

    @DeleteMapping("/post/{id}")
    public String deletePostWithId(@PathVariable Long id, Principal principal) {
        Optional<Post> optionalPost = postService.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                logger.info("Post deleted successfully: {}", post.getTitle());
                return "redirect:/blog/" + post.getUser().getUsername();
            } else {
                logger.warn("Access denied for user: {} to delete post: {}", principal.getName(), id);
                return "403";
            }
        } else {
            logger.error("Post not found for deletion: id = {}", id);
            return "error";
        }
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return principal != null && principal.getName().equals(post.getUser().getUsername());
    }
}



// package com.reljicd.controller;

// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import com.reljicd.service.PostService;
// import com.reljicd.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import javax.validation.Valid;
// import java.security.Principal;
// import java.util.Optional;

// @Controller
// public class PostController {

// private final PostService postService;
// private final UserService userService;

// @Autowired
// public PostController(PostService postService, UserService userService) {
// this.postService = postService;
// this.userService = userService;
// }

// @RequestMapping(value = "/newPost", method = RequestMethod.GET)
// public String newPost(Principal principal,
// Model model) {

// Optional<User> user = userService.findByUsername(principal.getName());

// if (user.isPresent()) {
// Post post = new Post();
// post.setUser(user.get());

// model.addAttribute("post", post);

// return "/postForm";

// } else {
// return "/error";
// }
// }

// @RequestMapping(value = "/newPost", method = RequestMethod.POST)
// public String createNewPost(@Valid Post post,
// BindingResult bindingResult) {

// if (bindingResult.hasErrors()) {
// return "/postForm";
// } else {
// postService.save(post);
// return "redirect:/blog/" + post.getUser().getUsername();
// }
// }

// @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
// public String editPostWithId(@PathVariable Long id,
// Principal principal,
// Model model) {

// Optional<Post> optionalPost = postService.findForId(id);

// if (optionalPost.isPresent()) {
// Post post = optionalPost.get();

// if (isPrincipalOwnerOfPost(principal, post)) {
// model.addAttribute("post", post);
// return "/postForm";
// } else {
// return "/403";
// }

// } else {
// return "/error";
// }
// }

// @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
// public String getPostWithId(@PathVariable Long id,
// Principal principal,
// Model model) {

// Optional<Post> optionalPost = postService.findForId(id);

// if (optionalPost.isPresent()) {
// Post post = optionalPost.get();

// model.addAttribute("post", post);
// if (isPrincipalOwnerOfPost(principal, post)) {
// model.addAttribute("username", principal.getName());
// }

// return "/post";

// } else {
// return "/error";
// }
// }

// @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
// public String deletePostWithId(@PathVariable Long id,
// Principal principal) {

// Optional<Post> optionalPost = postService.findForId(id);

// if (optionalPost.isPresent()) {
// Post post = optionalPost.get();

// if (isPrincipalOwnerOfPost(principal, post)) {
// postService.delete(post);
// return "redirect:/home";
// } else {
// return "/403";
// }

// } else {
// return "/error";
// }
// }

// private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
// return principal != null &&
// principal.getName().equals(post.getUser().getUsername());
// }
// }

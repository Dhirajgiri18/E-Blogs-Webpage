package com.reljicd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.service.PostService;
import com.reljicd.service.UserService;
import com.reljicd.util.Pager;

@RestController
@Controller
public class BlogController {

    private final UserService userService;
    private final PostService postService;

    @Autowired
    public BlogController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    /**
     * Display the blog for a specific user by username.
     *
     * @param username the username of the user
     * @param page     the page number for pagination
     * @param model    the model to pass data to the view
     * @return the name of the view to render
     */
    @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
    public String blogForUsername(@PathVariable String username,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        Optional<User> optionalUser = userService.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Page<Post> posts = postService.findByUserOrderedByDatePageable(user, page);
            Pager pager = new Pager(posts);

            model.addAttribute("pager", pager);
            model.addAttribute("user", user);
            model.addAttribute("posts", posts.getContent()); // Adding posts to the model

            return "posts"; // Removed leading slash for view name

        } else {
            model.addAttribute("errorMessage", "User not found");
            return "error"; // Return to a custom error page
        }
    }
}

// package com.reljicd.controller;

// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import com.reljicd.service.PostService;
// import com.reljicd.service.UserService;
// import com.reljicd.util.Pager;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

// import java.util.Optional;

// @Controller
// public class BlogController {

// private final UserService userService;

// private final PostService postService;

// @Autowired
// public BlogController(UserService userService, PostService postService) {
// this.userService = userService;
// this.postService = postService;
// }

// @RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
// public String blogForUsername(@PathVariable String username,
// @RequestParam(defaultValue = "0") int page,
// Model model) {

// Optional<User> optionalUser = userService.findByUsername(username);

// if (optionalUser.isPresent()) {
// User user = optionalUser.get();
// Page<Post> posts = postService.findByUserOrderedByDatePageable(user, page);
// Pager pager = new Pager(posts);

// model.addAttribute("pager", pager);
// model.addAttribute("user", user);

// return "/posts";

// } else {
// return "/error";
// }
// }
// }

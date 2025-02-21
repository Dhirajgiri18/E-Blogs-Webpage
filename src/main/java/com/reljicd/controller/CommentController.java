package com.reljicd.controller;

import java.security.Principal;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.reljicd.model.Comment;
import com.reljicd.model.Post;
import com.reljicd.model.User;
import com.reljicd.service.CommentService;
import com.reljicd.service.PostService;
import com.reljicd.service.UserService;

/**
 * Controller for managing comments on posts.
 */
@Controller
public class CommentController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public CommentController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    /**
     * Handles the creation of a new comment.
     *
     * @param comment        the comment to be created
     * @param bindingResult  the result of the validation
     * @return redirect to the post page or the comment form in case of errors
     */
    @PostMapping("/createComment")
    public String createNewComment(@Valid Comment comment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "commentForm"; // Changed to view name without leading slash
        } else {
            commentService.save(comment);
            return "redirect:/post/" + comment.getPost().getId();
        }
    }

    /**
     * Prepares the comment form for the given post ID.
     *
     * @param id        the ID of the post to comment on
     * @param principal the authenticated user
     * @param model     the model to hold attributes for the view
     * @return the comment form view or an error page
     */
    @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET) // Ensure you have the import for RequestMethod
    public String commentPostWithId(@PathVariable Long id, Principal principal, Model model) {
        Optional<Post> post = postService.findById(id);

        if (post.isPresent()) {
            Optional<User> user = userService.findByUsername(principal.getName());

            if (user.isPresent()) {
                Comment comment = new Comment();
                comment.setUser(user.get());
                comment.setPost(post.get());

                model.addAttribute("comment", comment);
                return "commentForm"; // Changed to view name without leading slash
            }
        }
        
        return "error"; // Redirecting to error view directly
    }
}




// package com.reljicd.controller;

// import com.reljicd.model.Comment;
// import com.reljicd.model.Post;
// import com.reljicd.model.User;
// import com.reljicd.service.CommentService;
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
// public class CommentController {

//     private final PostService postService;
//     private final UserService userService;
//     private final CommentService commentService;

//     @Autowired
//     public CommentController(PostService postService, UserService userService, CommentService commentService) {
//         this.postService = postService;
//         this.userService = userService;
//         this.commentService = commentService;
//     }

//     @RequestMapping(value = "/createComment", method = RequestMethod.POST)
//     public String createNewPost(@Valid Comment comment,
//                                 BindingResult bindingResult) {

//         if (bindingResult.hasErrors()) {
//             return "/commentForm";

//         } else {
//             commentService.save(comment);
//             return "redirect:/post/" + comment.getPost().getId();
//         }
//     }

//     @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
//     public String commentPostWithId(@PathVariable Long id,
//                                     Principal principal,
//                                     Model model) {

//         Optional<Post> post = postService.findForId(id);

//         if (post.isPresent()) {
//             Optional<User> user = userService.findByUsername(principal.getName());

//             if (user.isPresent()) {
//                 Comment comment = new Comment();
//                 comment.setUser(user.get());
//                 comment.setPost(post.get());

//                 model.addAttribute("comment", comment);

//                 return "/commentForm";

//             } else {
//                 return "/error";
//             }

//         } else {
//             return "/error";
//         }
//     }

// }

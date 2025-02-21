package com.reljicd.controller;

import com.reljicd.model.Post;
import com.reljicd.service.PostService;
import com.reljicd.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for managing the home page and displaying posts.
 */
@Controller
public class HomeController {

    private final PostService postService;

    @Autowired
    public HomeController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Displays the home page with paginated posts.
     *
     * @param page  the page number to display (default is 0)
     * @param model the model to hold attributes for the view
     * @return the home view name
     */
    @GetMapping("/home")
    public String home(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<Post> posts = postService.findAllOrderedByDatePageable(page);
        Pager pager = new Pager(posts);

        model.addAttribute("pager", pager);
        model.addAttribute("posts", posts.getContent()); // Add posts to the model for the view

        return "home"; // Changed to view name without leading slash
    }
}



// package com.reljicd.controller;

// import com.reljicd.model.Post;
// import com.reljicd.service.PostService;
// import com.reljicd.util.Pager;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// public class HomeController {

//     private final PostService postService;

//     @Autowired
//     public HomeController(PostService postService) {
//         this.postService = postService;
//     }

//     @GetMapping("/home")
//     public String home(@RequestParam(defaultValue = "0") int page,
//                        Model model) {

//         Page<Post> posts = postService.findAllOrderedByDatePageable(page);
//         Pager pager = new Pager(posts);

//         model.addAttribute("pager", pager);

//         return "/home";
//     }
// }

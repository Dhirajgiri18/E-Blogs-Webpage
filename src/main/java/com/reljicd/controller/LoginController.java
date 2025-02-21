package com.reljicd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Controller for handling login requests.
 */
@Controller
public class LoginController {

    /**
     * Displays the login page or redirects to the home page if the user is already logged in.
     *
     * @param principal the authenticated user's principal
     * @return the login view name or redirect to home
     */
    @GetMapping("/login")
    public String login(Principal principal) {
        // Redirect to home if user is already authenticated
        if (principal != null) {
            return "redirect:/home";
        }
        // Return the login view if not authenticated
        return "login"; // Changed to view name without leading slash
    }
}



// package com.reljicd.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;

// import java.security.Principal;

// @Controller
// public class LoginController {

//     @GetMapping("/login")
//     public String login(Principal principal) {

//         if (principal != null) {
//             return "redirect:/home";
//         }
//         return "/login";
//     }

// }

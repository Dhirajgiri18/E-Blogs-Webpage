package com.reljicd.controller;

import com.reljicd.model.User;
import com.reljicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Controller for user registration.
 */
@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Display registration form.
     *
     * @param model the model to populate the view
     * @return the registration view name
     */
    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration"; // Removed leading slash
    }

    /**
     * Handle registration form submission.
     *
     * @param user the user to create
     * @param bindingResult the result of the binding process
     * @param model the model to populate the view
     * @return the registration view name or redirect to a success page
     */
    @PostMapping("/registration")
    public String createNewUser(@Valid User user,
                                BindingResult bindingResult,
                                Model model) {
        // Check if email is already in use
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            bindingResult.rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
        }
        
        // Check if username is already in use
        if (userService.findByUsername(user.getUsername()).isPresent()) {
            bindingResult.rejectValue("username", "error.user",
                    "There is already a user registered with the username provided");
        }

        // Check for validation errors
        if (!bindingResult.hasErrors()) {
            // Registration successful, save user
            userService.save(user);

            model.addAttribute("successMessage", "User has been registered successfully");
            model.addAttribute("user", new User());
            return "registration"; // Return registration view or redirect to success page
        }

        // Return to registration form with errors
        return "registration"; // Removed leading slash
    }
}



// package com.reljicd.controller;

// import com.reljicd.model.User;
// import com.reljicd.service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import javax.validation.Valid;

// @Controller
// public class RegistrationController {

//     private final UserService userService;

//     @Autowired
//     public RegistrationController(UserService userService) {
//         this.userService = userService;
//     }

//     @RequestMapping(value = "/registration", method = RequestMethod.GET)
//     public String registration(Model model) {

//         model.addAttribute("user", new User());
//         return "/registration";
//     }

//     @RequestMapping(value = "/registration", method = RequestMethod.POST)
//     public String createNewUser(@Valid User user,
//                                 BindingResult bindingResult,
//                                 Model model) {

//         if (userService.findByEmail(user.getEmail()).isPresent()) {
//             bindingResult
//                     .rejectValue("email", "error.user",
//                             "There is already a user registered with the email provided");
//         }
//         if (userService.findByUsername(user.getUsername()).isPresent()) {
//             bindingResult
//                     .rejectValue("username", "error.user",
//                             "There is already a user registered with the username provided");
//         }

//         if (!bindingResult.hasErrors()) {
//             // Registration successful, save user
//             // Set user role to USER and set it as active
//             userService.save(user);

//             model.addAttribute("successMessage", "User has been registered successfully");
//             model.addAttribute("user", new User());
//         }

//         return "/registration";
//     }
// }

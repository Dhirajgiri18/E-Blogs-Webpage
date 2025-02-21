package com.reljicd.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Custom error controller to handle error pages.
 */
@Controller
public class BlogErrorController implements ErrorController {

    private static final String PATH = "/error";

    /**
     * Handles general errors.
     *
     * @return ModelAndView for the error page.
     */
    @RequestMapping(PATH)
    public ModelAndView error() {
        return new ModelAndView("error"); // Removed leading slash for view name
    }

    /**
     * Handles 403 Forbidden errors.
     *
     * @return ModelAndView for the 403 error page.
     */
    @GetMapping("/403")
    public ModelAndView error403() {
        return new ModelAndView("403"); // Removed leading slash for view name
    }

    // Optional in Spring Boot 2.3 and later
    // @Override
    // public String getErrorPath() {
    //     return PATH;
    // }
}




// package com.reljicd.controller;

// import org.springframework.boot.autoconfigure.web.ErrorController;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.ModelAndView;

// @RestController
// public class BlogErrorController implements ErrorController {

//     private static final String PATH = "/error";

//     @RequestMapping(PATH)
//     public ModelAndView error() {
//         return new ModelAndView("/error");
//     }

//     @GetMapping("/403")
//     public ModelAndView error403() {
//         return new ModelAndView("/403");
//     }

//     @Override
//     public String getErrorPath() {
//         return PATH;
//     }
// }

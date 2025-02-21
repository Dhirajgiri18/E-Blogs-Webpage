package com.reljicd.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Global exception handler for the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles all exceptions thrown in the application.
     *
     * @param throwable the exception that was thrown
     * @return a ModelAndView object with error information
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView handleException(final Throwable throwable) {
        // Log the exception details
        logger.error("Exception during execution of Spring application", throwable);

        // Create a ModelAndView for the error page
        ModelAndView modelAndView = new ModelAndView("error"); // Remove leading slash for logical view name
        String errorMessage = (throwable != null) ? throwable.getMessage() : "Unknown error";
        modelAndView.addObject("errorMessage", errorMessage);
        return modelAndView;
    }
}



// package com.reljicd.config;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.http.HttpStatus;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.servlet.ModelAndView;

// /**
//  * Global exception handler
//  */
// @ControllerAdvice
// public class GlobalExceptionHandler {

//     private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//     @ExceptionHandler(Throwable.class)
//     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//     public ModelAndView exception(final Throwable throwable) {

//         logger.error("Exception during execution of SpringSecurity application", throwable);

//         ModelAndView modelAndView = new ModelAndView("/error");
//         String errorMessage = (throwable != null ? throwable.toString() : "Unknown error");
//         modelAndView.addObject("errorMessage", errorMessage);
//         return modelAndView;
//     }

// }

package com.example.controller;

import com.example.exceptions.ContactNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public String error() throws Exception{
        throw new ContactNotFoundException("Sorry, We couldn't find your request!");
        //return "customError";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}

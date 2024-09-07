package com.toki.socialmedia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String homeControllerHandle(){
        return "Welcome to Toki Social Media";
    }

}

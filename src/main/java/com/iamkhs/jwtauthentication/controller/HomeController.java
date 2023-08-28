package com.iamkhs.jwtauthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "This is Home";
    }

    @GetMapping("/admin")
    public String admin(){
        return "This is admin page";
    }

    @GetMapping("/normal")
    public String normal(){
        return "This is normal user page";
    }
}

package com.test.compulynx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
    @GetMapping("/home")
    public String homePage() {
        System.out.println(" Getting the HomePage");
        return "<h2>Welcome Home. This page is accessible without being authenticated<h2>";
    }


}

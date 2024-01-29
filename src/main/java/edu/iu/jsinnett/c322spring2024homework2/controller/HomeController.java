package edu.iu.jsinnett.c322spring2024homework2.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public  String WelcomeMessage(){
        return "Hello! Welcome";
    }
}

package com.project.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



    @Controller
    public class PageController {

        @GetMapping("/")
        public String login(){
            return "login";
        }

        @GetMapping("/home")
        public String home(){
            return "home";
        }

        @GetMapping("/admin")
        public String admin(){
            return "admin";
        }
    }


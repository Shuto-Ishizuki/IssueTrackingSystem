package com.example.issuetrackingsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    // http://localhost:8080 アクセス時に実行される
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

package com.ppphuang.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String Index() {
        return "index";
    }

    @GetMapping("/blog")
    public String Blog() {
        int a = 10/0;
        return "blog";
    }
}

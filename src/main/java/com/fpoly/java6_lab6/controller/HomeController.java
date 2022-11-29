package com.fpoly.java6_lab6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/home/index")
    public String index(Model model){
        model.addAttribute("message","This is Home Page");
        return "home/index";
    }
    @RequestMapping("/home/about")
    public String about(Model model){
        model.addAttribute("message","This is introduction page");
        return "/home/index";
    }
}

package com.makin.makinschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping(value = {"", "/", "/home"})
    public String displayHomePage(Model model) {
        model.addAttribute("appName", "Makin School");
        model.addAttribute("currentPage", "home");
        return "home";
    }
}

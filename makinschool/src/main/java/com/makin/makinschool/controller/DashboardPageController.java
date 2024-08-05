package com.makin.makinschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class DashboardPageController {

    @RequestMapping("/dashboard")
    public String displayContactPage(Authentication authentication, Model model) {
        model.addAttribute("appName", "Makin School");
        model.addAttribute("username", authentication.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        return "dashboard";
    }
}

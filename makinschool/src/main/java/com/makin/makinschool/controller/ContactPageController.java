package com.makin.makinschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactPageController {

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact";
    }
}

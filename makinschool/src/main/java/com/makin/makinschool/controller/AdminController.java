package com.makin.makinschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("appName", "Makin School");
        modelAndView.addObject("currentPage", "displayClasses");
        return modelAndView;
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses(Model model) {
        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.addObject("appName", "Makin School");
        modelAndView.addObject("currentPage", "displayCourses");
        return modelAndView;
    }
}

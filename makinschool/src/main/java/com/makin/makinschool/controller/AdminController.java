package com.makin.makinschool.controller;

import com.makin.makinschool.model.MakinClass;
import com.makin.makinschool.service.MakinClassService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private MakinClassService makinClassService;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses() {
        List<MakinClass> makinClasses = makinClassService.getAllClasses();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("makinClasses", makinClasses);
        modelAndView.addObject("makinClass", new MakinClass());
        modelAndView.addObject("appName", "Makin School");
        modelAndView.addObject("currentPage", "displayClasses");
        return modelAndView;
    }

    @RequestMapping("/displayCourses")
    public ModelAndView displayCourses() {
        ModelAndView modelAndView = new ModelAndView("courses");
        modelAndView.addObject("appName", "Makin School");
        modelAndView.addObject("currentPage", "displayCourses");
        return modelAndView;
    }

    @RequestMapping("/addNewClass")
    public ModelAndView addNewClass(@ModelAttribute("makinClass") MakinClass makinClass) {
        makinClassService.addClass(makinClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int id) {
        makinClassService.deleteClass(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }
}

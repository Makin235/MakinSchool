package com.makin.makinschool.controller;

import com.makin.makinschool.model.Person;
import com.makin.makinschool.model.Profile;
import com.makin.makinschool.service.PersonService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller("profileControllerBean")
public class ProfileController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(HttpSession session, Model model) {
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = personService.getProfile(person);
        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", profile);
        modelAndView.addObject("appName", "Makin School");
        return modelAndView;
    }

    @RequestMapping("/updateProfile")
    public String updateProfile(
            @Valid @ModelAttribute("profile") Profile profile,
            Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "profile";
        }
        personService.setPerson(profile, session);
        return "redirect:/displayProfile";
    }
}

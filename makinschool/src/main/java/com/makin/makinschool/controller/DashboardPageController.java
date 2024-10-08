package com.makin.makinschool.controller;

import com.makin.makinschool.model.Person;
import com.makin.makinschool.service.PersonService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardPageController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/dashboard")
    public String displayContactPage(Authentication authentication, HttpSession session, Model model) {
        Person person = personService.getPersonByEmail(authentication.getName());
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        if (null != person.getMakinClass() && null != person.getMakinClass().getName()) {
            model.addAttribute("enrolledClass", person.getMakinClass().getName());
        }
        model.addAttribute("appName", "Makin School");
        model.addAttribute("currentPage", "dashboard");
        session.setAttribute("loggedInPerson", person);
        logMessages();
        return "dashboard";
    }

    private void logMessages() {
        log.error("This is an error message.");
        log.warn("This is a warn message.");
        log.info("This is an info message.");
        log.trace("This is a trace message.");
    }
}

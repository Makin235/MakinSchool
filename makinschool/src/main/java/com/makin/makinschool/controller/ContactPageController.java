package com.makin.makinschool.controller;

import com.makin.makinschool.model.ContactModel;
import com.makin.makinschool.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ContactPageController {

    private final ContactService contactService;

    @Autowired
    public ContactPageController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping("/contact")
    public String displayContactPage() {
        return "contact";
    }

    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessage(ContactModel contactModel) {
        contactService.saveMessageDetails(contactModel);
        return new ModelAndView("redirect:/contact");
    }
}

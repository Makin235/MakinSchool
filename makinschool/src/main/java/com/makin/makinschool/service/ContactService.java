package com.makin.makinschool.service;

import com.makin.makinschool.controller.ContactPageController;
import com.makin.makinschool.model.ContactModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
@Service
@RequestScope
public class ContactService {

    private int counter = 0;

    public ContactService() {
        System.out.println("ContactService bean initialized.");
    }

    /**
     * Save Contact details into DB
     * @param contactModel
     * @return boolean
     */
    public boolean saveMessageDetails(ContactModel contactModel) {
        boolean isSaved = true;
        log.info(contactModel.toString());
        return isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}

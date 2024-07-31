package com.makin.makinschool.service;

import com.makin.makinschool.controller.ContactPageController;
import com.makin.makinschool.model.ContactModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private static Logger log = LoggerFactory.getLogger(ContactPageController.class);

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
}

package com.makin.makinschool.service;

import com.makin.makinschool.controller.ContactPageController;
import com.makin.makinschool.model.ContactModel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

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

package com.makin.makinschool.service;

import com.makin.makinschool.MakinSchoolConstants;
import com.makin.makinschool.controller.ContactPageController;
import com.makin.makinschool.model.ContactModel;
import com.makin.makinschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Save Contact details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(ContactModel contact) {
        boolean isSaved = false;
        contact.setStatus(MakinSchoolConstants.OPEN);
        contact.setCreatedBy(MakinSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }
        log.info(contact.toString());
        return isSaved;
    }

    /**
     * Get Contact Messages from DB
     * @return List<ContactModel>
     */
    public List<ContactModel> findMsgsWithOpenStatus() {
        List<ContactModel> contactMsgs = contactRepository.findMsgsWithOpenStatus(MakinSchoolConstants.OPEN);
        return contactMsgs;
    }
}

package com.makin.makinschool.service;

import com.makin.makinschool.constant.MakinSchoolConstants;
import com.makin.makinschool.model.ContactModel;
import com.makin.makinschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        ContactModel savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContact_id() > 0) {
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
        List<ContactModel> contactMsgs = contactRepository.findByStatus(MakinSchoolConstants.OPEN);
        return contactMsgs;
    }

    /**
     * Update contact message status
     * @param contactId, updatedBy
     * @return boolean
     */
    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        Optional<ContactModel> contact = contactRepository.findById(contactId);
        contact.ifPresent(contactModel -> {
            contactModel.setStatus(MakinSchoolConstants.CLOSE);
        });
//
        ContactModel updatedContact = contactRepository.save(contact.get());
        if (null != updatedContact && updatedContact.getUpdatedBy() != null) {
            isUpdated = true;
        }
        return isUpdated;
    }
}

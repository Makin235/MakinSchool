package com.makin.makinschool.service;

import com.makin.makinschool.config.MakinSchoolProps;
import com.makin.makinschool.constant.MakinSchoolConstants;
import com.makin.makinschool.model.Contact;
import com.makin.makinschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Value("${makinschool.pagination.pageSize}")
    private int defaultPageSize;

    @Autowired
    private MakinSchoolProps makinSchoolProps;

    /**
     * Save Contact details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(MakinSchoolConstants.OPEN);
        Contact savedContact = contactRepository.save(contact);
        if (null != savedContact && savedContact.getContactId() > 0) {
            isSaved = true;
        }
        log.info(contact.toString());
        return isSaved;
    }

    /**
     * Get Contact Messages from DB
     * @return List<Contact>
     */
    public Page<Contact> findMsgsWithOpenStatus(int pageNum, String sortField, String sortDir) {
        int pageSize = defaultPageSize;
        if (null != makinSchoolProps.getContact()
                && null != makinSchoolProps.getContact().get("pageSize")) {
            pageSize = Integer.parseInt(makinSchoolProps.getContact().get("pageSize").trim());
        }
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findByStatusWithQuery(MakinSchoolConstants.OPEN, pageable);
        return msgPage;
    }

    /**
     * Update contact message status
     * @param contactId, updatedBy
     * @return boolean
     */
    public boolean updateMsgStatus(int contactId) {
        boolean isUpdated = false;
        int rows = contactRepository.updateMsgStatus(MakinSchoolConstants.CLOSE, contactId);
        if (rows > 0) {
            isUpdated = true;
        }
        return isUpdated;
    }
}

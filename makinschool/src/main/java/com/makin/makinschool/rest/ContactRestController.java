package com.makin.makinschool.rest;

import com.makin.makinschool.constant.MakinSchoolConstants;
import com.makin.makinschool.model.Contact;
import com.makin.makinschool.model.Response;
import com.makin.makinschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@CrossOrigin(origins = "*")
public class ContactRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getMsgsByStatus")
    public List<Contact> getMsgsByStatus(@RequestBody Contact contact) {
        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(
            @RequestHeader("invocationFrom") String invocationForm,
            @Valid @RequestBody Contact contact) {
         log.info(String.format("Header invocationForm = %s", invocationForm));
         contactRepository.save(contact);
         Response response = new Response();
         response.setStatusCode("200");
         response.setStatusMsg("Message saved successfully");
         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .header("isMsgSaved", "true")
                 .body(response);
    }

    @DeleteMapping("/deleteMsg")
    public ResponseEntity<Response> deleteMsg(RequestEntity<Contact> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value)->{
            log.info(String.format("Header '%s' = %s",
                    key, value.stream().collect(Collectors.joining("|"))));
        });
        Contact contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgDeleted", "true")
                .body(response);
    }

    @PatchMapping("/closeMsg")
    public ResponseEntity<Response> closeMsg(@RequestBody Contact contactReq) {
        Response response = new Response();
        Optional<Contact> contact = contactRepository.findById(contactReq.getContactId());
        if (contact.isPresent()) {
            contact.get().setStatus(MakinSchoolConstants.CLOSE);
            contactRepository.save(contact.get());
            response.setStatusCode("200");
            response.setStatusMsg("Message closed successfully!");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.setStatusCode("400");
            response.setStatusMsg("Invalid contact id received.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}

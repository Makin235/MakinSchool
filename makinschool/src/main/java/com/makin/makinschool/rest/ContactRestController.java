package com.makin.makinschool.rest;

import com.makin.makinschool.model.ContactModel;
import com.makin.makinschool.model.Response;
import com.makin.makinschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(path = "/api/contact")
public class ContactRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/getMessagesByStatus")
    public List<ContactModel> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getMsgsByStatus")
    public List<ContactModel> getMsgsByStatus(@RequestBody ContactModel contact) {
        if (null != contact && null != contact.getStatus()) {
            return contactRepository.findByStatus(contact.getStatus());
        } else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<Response> saveMsg(
            @RequestHeader("invocationForm") String invocationForm,
            @Valid @RequestBody ContactModel contact) {
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
    public ResponseEntity<Response> deleteMsg(RequestEntity<ContactModel> requestEntity) {
        HttpHeaders headers = requestEntity.getHeaders();
        headers.forEach((key, value)->{
            log.info(String.format("Header '%s' = %s",
                    key, value.stream().collect(Collectors.joining("|"))));
        });
        ContactModel contact = requestEntity.getBody();
        contactRepository.deleteById(contact.getContactId());
        Response response = new Response();
        response.setStatusCode("200");
        response.setStatusMsg("Message deleted successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgDeleted", "true")
                .body(response);
    }
}

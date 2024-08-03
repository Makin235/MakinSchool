package com.makin.makinschool.model;

import lombok.Data;

@Data
public class ContactModel {

    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
}

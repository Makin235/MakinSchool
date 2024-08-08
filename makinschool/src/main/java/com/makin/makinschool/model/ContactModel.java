package com.makin.makinschool.model;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactModel extends BaseEntity {

    private int contact_id;

    @NotBlank(message = "Name must not be blank.")
    @Size(min = 2, message = "Name must be at least 2 characters long.")
    private String name;

    @NotBlank(message = "Mobile number must not be blank.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits.")
    private String mobileNum;

    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Please provide a valid email.")
    private String email;

    @NotBlank(message = "Subject must not be blank.")
    private String subject;

    @NotEmpty(message = "Subject must not be empty.")
    private String message;

    private String status;
}

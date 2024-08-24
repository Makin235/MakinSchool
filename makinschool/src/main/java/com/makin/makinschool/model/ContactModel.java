package com.makin.makinschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "contact_msg")
@Data
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "SqlResultSetMapping.count", columns = @ColumnResult(name = "cnt"))
})
@NamedQueries({
    @NamedQuery(name = "ContactModel.findOpenMsgs",
            query = "SELECT c FROM ContactModel c WHERE c.status = :status"),
    @NamedQuery(name = "ContactModel.updateMsgStatus",
            query = "UPDATE ContactModel c SET c.status = ?1 WHERE c.contactId = ?2")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "ContactModel.findOpenMsgsNative",
        query = "SELECT * FROM contact_msg c WHERE c.status = :status",
        resultClass = ContactModel.class),
    @NamedNativeQuery(name = "ContactModel.findOpenMsgsNative.count",
        query = "SELECT count(*) as cnt FROM contact_msg c WHERE c.status = :status",
        resultSetMapping = "SqlResultSetMapping.count"),
    @NamedNativeQuery(name = "ContactModel.updateMsgStatusNative",
        query = "UPDATE contact_msg c SET c.status = ?1 WHERE c.contact_Id = ?2")
})

public class ContactModel extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "contact_id")
    private int contactId;

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

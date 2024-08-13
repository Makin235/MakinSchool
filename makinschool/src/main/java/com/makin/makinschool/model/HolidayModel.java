package com.makin.makinschool.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "holidays")
@Data
public class HolidayModel extends BaseEntity {

    @Id
    private String day;

    private String reason;

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL,
        FEDERAL
    }
}

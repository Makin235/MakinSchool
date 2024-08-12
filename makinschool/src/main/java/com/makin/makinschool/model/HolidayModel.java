package com.makin.makinschool.model;

import lombok.Data;

@Data
public class HolidayModel extends BaseEntity {

    private String day;
    private String reason;
    private Type type;

    public enum Type {
        FESTIVAL,
        FEDERAL
    }
}

package com.makin.makinschool.model;

import lombok.Data;

@Data
public class HolidayModel {

    private final String day;
    private final String reason;
    private final Type type;

    public enum Type {
        FESTIVAL,
        FEDERAL
    }
}

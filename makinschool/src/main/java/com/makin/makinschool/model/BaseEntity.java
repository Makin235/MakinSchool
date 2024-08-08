package com.makin.makinschool.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class BaseEntity {

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
}

package com.makin.makinschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Component("makinSchholProps")
@Data
@PropertySource("classpath:custom.properties")
@ConfigurationProperties(prefix = "makinschool")
@Validated
public class MakinSchoolProps {

    @Min(value = 3, message = "must be at least 3")
    @Max(value = 25, message = "must be at most 25")
    private int pageSize;
    private Map<String, String> contact;
    private Map<String, String> msg;
    private List<String> branches;
}

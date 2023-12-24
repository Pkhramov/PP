package com.example.adapterservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "discount")
public class DiscasdProperties {
    private Long defaultDiscount;
    private Long birthdayThisMonthDiscount;
}

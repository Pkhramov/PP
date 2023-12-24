package com.example.adapterservice.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientsInfo {
    private String clientId;
    private String name;
    private String middleName;
    private String surname;
    private Long age;
    private LocalDate birthday;
    private String phone;
}

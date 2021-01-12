package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAgentRequest {

    private String username;

    private String password;

    private String rePassword;

    private String name;

    private String pib;

    private LocalDate dateFounded;

    private String bankAccountNumber;

    private String address;
}

package com.example.demo.dto.request;
import lombok.Data;

import java.util.UUID;

@Data
public class MailDTO {

    private Long id;

    private String role;

    private String username;

    private String firstName;

    private String lastName;

    private String name;
}

package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserResponse {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String ssn;
    private String address;
    private String userRole;

}

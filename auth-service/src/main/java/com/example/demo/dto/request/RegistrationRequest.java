package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    String username;
    String password;
    String rePassword;
    String firstName;
    String lastName;
    String ssn;
    String address;
    String city;
    String country;

}

package com.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherResponse {

    //zajednicka
    private Long id;
    private String address;
    //agent
    private String name;
    private Date dateFounded;
    //simple-user
    private String username;
    private String firstName;
    private String lastName;
    private String ssn;
    private int numOfAds;


}

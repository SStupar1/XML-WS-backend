package com.example.demo.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Agent {

    private Long id;

    private String name;

    private LocalDate dateFounded;

    private String address;

    private String bankAccountNumber;

}

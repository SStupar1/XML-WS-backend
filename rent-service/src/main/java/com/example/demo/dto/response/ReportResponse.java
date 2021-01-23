package com.example.demo.dto.response;

import com.example.demo.dto.client.Ad;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportResponse {
    private Ad ad;
    private double price;
}

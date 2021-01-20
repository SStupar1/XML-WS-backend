package com.example.demo.dto.response;

import com.example.demo.entity.Ad;
import com.example.demo.util.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String content;

    private Long customerId;

    private boolean simpleUser;

    private AdResponse ad;

    private RequestStatus status;
}

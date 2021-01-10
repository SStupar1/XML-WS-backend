package com.example.demo.dto.response;

import com.example.demo.entity.Ad;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long id;

    private String content;

    private Long userId;

    private AdResponse ad;

    private boolean simpleUser;

}

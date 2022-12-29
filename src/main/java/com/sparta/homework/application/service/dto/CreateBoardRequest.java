package com.sparta.homework.application.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateBoardRequest {
    private String title;
    private String writer;
    private String password;
    private String content;

}

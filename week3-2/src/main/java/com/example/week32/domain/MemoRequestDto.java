package com.example.week32.domain;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String username;
    private String contents;
    private String title;
    private Long password;
}

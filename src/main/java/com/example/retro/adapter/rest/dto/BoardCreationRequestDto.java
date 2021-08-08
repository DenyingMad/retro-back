package com.example.retro.adapter.rest.dto;

import lombok.Data;

@Data
public class BoardCreationRequestDto {
    private String name;
    private int votesPerUser;
}

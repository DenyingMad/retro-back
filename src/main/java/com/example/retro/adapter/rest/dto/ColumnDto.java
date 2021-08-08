package com.example.retro.adapter.rest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ColumnDto {
    private String name;
    private List<CardDto> cards;
}

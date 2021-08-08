package com.example.retro.adapter.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BoardDto {
    private Long id;
    private String name;
    private int votesPerUser;
    private LocalDate creationDate;
    private List<ColumnDto> columns;
}

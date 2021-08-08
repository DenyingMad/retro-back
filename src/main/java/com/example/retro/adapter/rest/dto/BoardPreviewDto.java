package com.example.retro.adapter.rest.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class BoardPreviewDto {
    private Long id;
    private String name;
    private int votesPerUser;
    private LocalDate creationDate;
}

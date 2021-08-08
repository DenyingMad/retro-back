package com.example.retro.adapter.rest.dto;

import com.example.retro.domain.Color;
import lombok.Data;

import java.util.List;

@Data
public class CardDto {
    private String text;
    private int votes;
    private Color color;
    private List<CommentDto> comments;
}

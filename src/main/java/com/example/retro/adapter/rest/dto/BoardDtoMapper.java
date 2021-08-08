package com.example.retro.adapter.rest.dto;

import com.example.retro.domain.Board;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class BoardDtoMapper {
    private final ModelMapper modelMapper;

    public BoardDtoMapper() {
        modelMapper = new ModelMapper();
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public BoardDto mapToDto(Board source) {
        return modelMapper.map(source, BoardDto.class);
    }

    public BoardPreviewDto mapToPreviewDto(Board source) {
        return modelMapper.map(source, BoardPreviewDto.class);
    }
}

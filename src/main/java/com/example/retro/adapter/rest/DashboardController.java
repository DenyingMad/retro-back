package com.example.retro.adapter.rest;

import com.example.retro.adapter.rest.dto.*;
import com.example.retro.app.api.BoardService;
import com.example.retro.app.api.PageableQuery;
import com.example.retro.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class DashboardController {
    private final BoardService boardService;
    private final BoardDtoMapper mapper;

    @GetMapping()
    public CollectionResponseDto<BoardPreviewDto> getAllBoards(@RequestParam(required = false) Integer pageIndex,
        @RequestParam(required = false) Integer pageSize) {
        PageableQuery<Board> boards = boardService.getAllBoards(pageIndex, pageSize);

        List<BoardPreviewDto> boardPreviews = boards.getContent().stream()
            .map(mapper::mapToPreviewDto)
            .collect(Collectors.toList());
        PagingDto paging = getPaging(pageIndex, pageSize, boards.getTotalPages(), boards.getTotalItems());

        log.info("Get all boards -> {}", boardPreviews);

        return new CollectionResponseDto<>(boardPreviews, paging);
    }

    @PostMapping()
    public BoardDto createBoard(@RequestBody BoardCreationRequestDto boardCreationRequestDto) {
        Board newBoard = boardService.createBoard(boardCreationRequestDto.getName(), boardCreationRequestDto.getVotesPerUser());
        return mapper.mapToDto(newBoard);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private PagingDto getPaging(Integer pageIndex, Integer pageSize, Integer pageCount, Long totalCount) {
        if (pageIndex == null || pageSize == null) {
            return null;
        }
        return new PagingDto(pageIndex, pageSize, pageCount, totalCount);
    }
}

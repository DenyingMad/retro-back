package com.example.retro.adapter.rest;

import com.example.retro.AbstractIntegrationTest;
import com.example.retro.adapter.rest.dto.BoardCreationRequestDto;
import com.example.retro.adapter.rest.dto.BoardDto;
import com.example.retro.adapter.rest.dto.BoardPreviewDto;
import com.example.retro.adapter.rest.dto.CollectionResponseDto;
import com.example.retro.domain.Board;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BoardIntegrationTest extends AbstractIntegrationTest {
    private static final String BOARD_NAME_1 = "1 Board Name";
    private static final String BOARD_NAME_2 = "2 Board Name";
    private static final String BOARD_NAME_3 = "3 Board Name";

    @Test
    void createBoard() throws Exception {
        BoardCreationRequestDto creationRequestDto = getBoardCreationRequestDto(BOARD_NAME_1);

        MockHttpServletResponse response = performCreateBoardAndGetResult(creationRequestDto);
        BoardDto dtoResponse = getObject(response, new TypeReference<>() {
        });

        assertNotNull(dtoResponse);
        assertBoardCreated(dtoResponse.getId());
    }

    @Test
    void getAllBoards() throws Exception {
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_2));
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_3));
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_1));

        MockHttpServletResponse firstPageResponse = performGetAllBoards(0, 2);
        MockHttpServletResponse secondPageResponse = performGetAllBoards(1, 2);
        CollectionResponseDto<BoardPreviewDto> firstPageResponseDto = getObject(firstPageResponse, new TypeReference<>() {
        });
        CollectionResponseDto<BoardPreviewDto> secondPageResponseDto = getObject(secondPageResponse, new TypeReference<>() {
        });

        assertEquals(2, firstPageResponseDto.getData().size());
        assertEquals(1, secondPageResponseDto.getData().size());
        assertEquals(BOARD_NAME_1, firstPageResponseDto.getData().get(0).getName());
        assertEquals(BOARD_NAME_2, firstPageResponseDto.getData().get(1).getName());
        assertEquals(BOARD_NAME_3, secondPageResponseDto.getData().get(0).getName());
        assertEquals(2, firstPageResponseDto.getPaging().getPageCount());
        assertEquals(3, firstPageResponseDto.getPaging().getTotalItemsCount());
    }

    @Test
    void getAllBoards_withoutPaging() throws Exception {
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_2));
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_3));
        performCreateBoard(getBoardCreationRequestDto(BOARD_NAME_1));

        MockHttpServletResponse response = performGetAllBoards_withoutPaging();
        CollectionResponseDto<BoardPreviewDto> responseDto = getObject(response, new TypeReference<>() {
        });

        assertEquals(3, responseDto.getData().size());
        assertEquals(BOARD_NAME_1, responseDto.getData().get(0).getName());
        assertEquals(BOARD_NAME_2, responseDto.getData().get(1).getName());
        assertEquals(BOARD_NAME_3, responseDto.getData().get(2).getName());
        assertNull(responseDto.getPaging());
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void assertBoardCreated(Long id) {
        Board board = boardRepository.findById(id).get();
        assertEquals(BOARD_NAME_1, board.getName());
        assertEquals(6, board.getVotesPerUser());
        assertNotNull(board.getCreationDate());
    }

    private MockHttpServletResponse performCreateBoardAndGetResult(BoardCreationRequestDto boardCreationRequestDto) throws Exception {
        return performCreateBoard(boardCreationRequestDto)
            .andExpect(status().isOk())
            .andReturn().getResponse();
    }

    private BoardCreationRequestDto getBoardCreationRequestDto(String boardName) {
        BoardCreationRequestDto creationRequestDto = new BoardCreationRequestDto();
        creationRequestDto.setName(boardName);
        creationRequestDto.setVotesPerUser(6);
        return creationRequestDto;
    }

    private void cleanBoardsDateBase() {
        boardRepository.deleteAll();
    }
}

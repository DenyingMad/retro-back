package com.example.retro;

import com.example.retro.adapter.jpa.BoardRepository;
import com.example.retro.adapter.rest.dto.BoardCreationRequestDto;
import com.example.retro.fw.RetroApplication;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RetroApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(profiles = {"local", "test"})
public abstract class AbstractIntegrationTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    protected BoardRepository boardRepository;

    @BeforeEach
    void clearDateBase() {
        boardRepository.deleteAll();
    }

    protected ResultActions performCreateBoard(BoardCreationRequestDto boardCreationRequestDto) throws Exception {
        String jsonContent = objectMapper.writeValueAsString(boardCreationRequestDto);
        return mvc.perform(post("/boards")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonContent));
    }

    protected MockHttpServletResponse performGetAllBoards(Integer pageIndex, Integer pageSize) throws Exception {
        return mvc.perform(get("/boards")
            .queryParam("pageIndex", String.valueOf(pageIndex))
            .queryParam("pageSize", String.valueOf(pageSize)))
            .andExpect(status().isOk())
            .andReturn().getResponse();
    }

    protected MockHttpServletResponse performGetAllBoards_withoutPaging() throws Exception {
        return mvc.perform(get("/boards"))
            .andExpect(status().isOk())
            .andReturn().getResponse();
    }

    protected <T> T getObject(MockHttpServletResponse response, TypeReference<T> type) {
        try {
            String jsonResponse = response.getContentAsString();
            return objectMapper.readValue(jsonResponse, type);
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

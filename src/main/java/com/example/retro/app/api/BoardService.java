package com.example.retro.app.api;

import com.example.retro.domain.Board;


public interface BoardService {
    PageableQuery<Board> getAllBoards(Integer pageNumber, Integer pageSize);
    Board createBoard(String boardName, int votesPerUser);
    Board getBoard(Long id);
}

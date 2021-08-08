package com.example.retro.app.impl;

import com.example.retro.adapter.jpa.BoardRepository;
import com.example.retro.app.api.BoardService;
import com.example.retro.app.api.PageableQuery;
import com.example.retro.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public PageableQuery<Board> getAllBoards(Integer pageIndex, Integer pageSize) {
        Sort sorting = Sort.by("creationDate").descending().and(Sort.by("name"));
        if (pageIndex == null || pageSize == null) {
            List<Board> boards = (List<Board>) boardRepository.findAll(sorting);
            return new PageableQuery<>(boards);
        }
        Page<Board> boardPage = boardRepository.findAll(PageRequest.of(pageIndex, pageSize, sorting));
        return new PageableQuery<>(boardPage.getContent(), boardPage.getTotalPages(), boardPage.getTotalElements());
    }

    @Override
    @Transactional
    public Board createBoard(String boardName, int votesPerUser) {
        Board board = new Board();
        board.setName(boardName);
        board.setVotesPerUser(votesPerUser);
        board.setCreationDate(LocalDate.now());
        return boardRepository.save(board);
    }

    @Override
    public Board getBoard(Long id) {
        return null;
    }
}

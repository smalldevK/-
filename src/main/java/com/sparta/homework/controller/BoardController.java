package com.sparta.homework.controller;

import com.sparta.homework.application.service.dto.BoardResponse;
import com.sparta.homework.application.service.dto.CreateBoardRequest;
import com.sparta.homework.application.service.BoardService;
import com.sparta.homework.application.service.dto.UpdateBoardRequest;
import com.sparta.homework.application.service.entity.Board;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("boards") // Create Boards -> 게시물 생성
    //createboardrequest를 전달받음
    public void createBoard(@RequestBody CreateBoardRequest createBoardRequest) {
        boardService.createBoard(createBoardRequest);
    }

    @GetMapping("boards/{boardId}")
    public BoardResponse getBoard(@PathVariable Long boardId) {
        return boardService.getBoard(boardId);
    }

    @GetMapping("boards")
    public List<BoardResponse> getBoardList() {
        return boardService.getBoardList();
    }

    @PutMapping("boards/{boardId}")
    public void updateBoard(@PathVariable Long boardId, @RequestBody UpdateBoardRequest updateBoardRequest) {
        boardService.updateBoard(boardId, updateBoardRequest);
    }

    @DeleteMapping("boards/{boardId}")
    public void deleteBoard(@PathVariable Long boardId, @RequestParam String password) {
        boardService.deleteBoard(boardId, password);
    }
}

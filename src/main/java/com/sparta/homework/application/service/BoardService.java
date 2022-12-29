package com.sparta.homework.application.service;

import com.sparta.homework.application.service.dto.BoardResponse;
import com.sparta.homework.application.service.dto.CreateBoardRequest;
import com.sparta.homework.application.service.dto.UpdateBoardRequest;
import com.sparta.homework.application.service.entity.Board;
import com.sparta.homework.repository.BoardRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 작성
    public void createBoard(CreateBoardRequest createBoardRequest) {
        Board board = new Board(createBoardRequest.getTitle(), createBoardRequest.getWriter(), createBoardRequest.getPassword(), createBoardRequest.getContent());
        boardRepository.save(board);
    }

    public BoardResponse getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물 없음"));
        return new BoardResponse(board);
    }

    //업데이트
    @Transactional
    public void updateBoard(Long boardId, UpdateBoardRequest updateBoardRequest) {
        Board boardSaved = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물 없음"));
        //수정할 데이터와 데이터 비밀번호를 같이 보내서 일치여부 확인
        if (boardSaved.isValidPassword(updateBoardRequest.getPassword())) {
            boardSaved.update(updateBoardRequest.getTitle(), updateBoardRequest.getWriter(), updateBoardRequest.getContent());
//            boardRepository.save(boardSaved); Transactional을 달아주면 안써도 됨
        }else {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
    }

    public void deleteBoard(Long boardId, String password) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물 없음"));
        if (board.isValidPassword(password)) {
            boardRepository.delete(board);
        }else {
            throw new IllegalArgumentException("패스워드가 틀렸습니다.");
        }
    }

    public List<BoardResponse> getBoardList() {
        List<Board> boardList = boardRepository.findAllByOrderByDateCreatedDesc();
        List<BoardResponse> boardResponseList = new ArrayList<>();
        for (Board board : boardList) {
            boardResponseList.add(new BoardResponse(board));
        }
        return boardResponseList;
    }
}

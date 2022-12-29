package com.sparta.homework.repository;


import com.sparta.homework.application.service.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//게시글 저장
public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByDateCreatedDesc();
}

package com.sparta.homework.application.service.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
//제목, 작성자명, 비밀번호, 작성내용 필요
public class Board extends TimeStamp{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id = 0L;


    private String title;
    private String writer;
    private String password;
    private String content;

    public Board() {

    }

    public Board(String title, String writer, String password, String content) {
        this.title = title;
        this.writer = writer;
        this.password = password;
        this.content = content;
    }

    // 제목, 작성자명, 작성내용 수정
    public void update(String title, String writer, String content) {
        this.title = title;
        this.writer = writer;
        this.content = content;
    }

    public boolean isValidPassword(String inputPassword) {
        if (inputPassword.equals(this.password)) return true;
        else return false;
    }
}

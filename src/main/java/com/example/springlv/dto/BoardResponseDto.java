package com.example.springlv.dto;

import com.example.springlv.entity.Board;
import com.example.springlv.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String title;
    private String content;
    private Long userid;
    private String Username;

    public BoardResponseDto(Board board, User user) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userid = board.getUserid();
        this.Username = user.getUsername();
    }

}
package com.example.springlv.entity;

import com.example.springlv.dto.BoardRequestDto;
import com.example.springlv.dto.BoardResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Long userid;

    public Board(BoardRequestDto boardRequestDto, Long userId){
        this.title = boardRequestDto.getTitle();
        this.content = boardRequestDto.getContent();
        this.userid = userId;
    }

    public void update(BoardRequestDto boardRequestDto) {
        this.content = boardRequestDto.getContent();
        this.title = boardRequestDto.getTitle();
    }
}

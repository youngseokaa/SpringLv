package com.example.springlv.controller;

import com.example.springlv.dto.BoardRequestDto;
import com.example.springlv.dto.BoardResponseDto;
import com.example.springlv.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public BoardResponseDto boardwrite(@RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request) {
        return boardService.boardwrite(boardRequestDto, request);
    }

    @GetMapping("/read")
    public List<BoardResponseDto> Boardinquire() {
        return boardService.boardinquire();
    }

    @PutMapping("/revise/{id}")
    public BoardResponseDto boardRevise(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, HttpServletRequest request){
        return boardService.boardrevise(id,boardRequestDto,request);
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Object> boardDelete(@PathVariable Long id, HttpServletRequest request){
        return boardService.boarddelete(id,request);
    }
}
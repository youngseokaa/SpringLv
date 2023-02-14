package com.example.springlv.service;

import com.example.springlv.dto.BoardRequestDto;
import com.example.springlv.dto.BoardResponseDto;
import com.example.springlv.dto.SignupRequestDto;
import com.example.springlv.entity.Board;
import com.example.springlv.entity.User;
import com.example.springlv.jwt.JwtUtil;
import com.example.springlv.repository.BoardRepository;
import com.example.springlv.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BoardService {
    final private BoardRepository boardRepository;
    final private JwtUtil jwtUtil;
    final private UserRepository userRepository;

    @Transactional
    public BoardResponseDto boardwrite(BoardRequestDto boardRequestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);

            } else {
                throw new IllegalArgumentException("token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            // 요청받은 DTO 로 DB에 저장할 객체 만들기
            Board board = boardRepository.saveAndFlush(new Board(boardRequestDto, user.getId()));

            return new BoardResponseDto(board, user);
        } else {
            return null;
        }
    }

    @Transactional
    public List<BoardResponseDto> boardinquire() {
        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();

        List<Board> board = boardRepository.findAllBy();
        for (int i = 0; i < board.size(); i++) {
            Optional<User> user = userRepository.findById(board.get(i).getUserid());
            boardResponseDtos.add(new BoardResponseDto(board.get(i), user.get()));
        }
        return boardResponseDtos;
    }

    @Transactional
    public BoardResponseDto boardrevise(Long id, BoardRequestDto boardRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            Board board = boardRepository.findByIdAndUserid(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
            );

            board.update(boardRequestDto);


            return new BoardResponseDto(board, user);
        } else {
            return null;
        }

    }

    public Map<String, Object> boarddelete(Long id, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다")
            );
            Board board = boardRepository.findByIdAndUserid(id, user.getId()).orElseThrow(
                    () -> new NullPointerException("해당 상품은 존재하지 않습니다.")
            );

        }
        boardRepository.deleteById(id);
        Map<String,Object> subject = new HashMap<>();
        subject.put("Success","true");
        return subject;
    }
}
//    public List<BoardResponseDto> boardinquire() {
//        List<BoardResponseDto> boardResponseDtos = new ArrayList<>();
//
//        List<Board> board = boardRepository.findAllBy();
//        List<User> user = userRepository.findAllBy();
//        for(int i = 0; i < board.size();i++){
//            for(int j = 0; j<user.size();j++) {
//                if(Objects.equals(board.get(i).getUserid(), user.get(j).getId())) {
//                    boardResponseDtos.add(new BoardResponseDto(board.get(i), user.get(j)));
//                }else{
//                    continue;
//                }
//            }
//        }
//        return boardResponseDtos;
//    }
//}
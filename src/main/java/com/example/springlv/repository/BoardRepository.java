package com.example.springlv.repository;

import com.example.springlv.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUserid(Long userId);
    Optional<Board> findByIdAndUserid(Long userid, Long id);
    List<Board> findAllBy();
}

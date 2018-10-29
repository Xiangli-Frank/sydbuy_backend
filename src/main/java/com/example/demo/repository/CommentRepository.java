package com.example.demo.repository;

import com.example.demo.entity.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface CommentRepository extends JpaRepository<CommentDTO, Integer> {

    List<CommentDTO> findAllByCommodity(@Param("commodity")String commodity);
}

package com.example.demo.repository;

import com.example.demo.entity.CommodityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author haocunli
 * @date created in 2018/10/20 17:35
 * @since 1.0.0
 */
public interface CommodityRepository extends JpaRepository<CommodityDTO, String> {

    Page<CommodityDTO> findAllByUsername(@Param("username")String username, Pageable pageable);
    List<CommodityDTO> findAllByUsername(@Param("username")String username);
}

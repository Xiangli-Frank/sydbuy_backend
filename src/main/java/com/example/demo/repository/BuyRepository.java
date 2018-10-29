package com.example.demo.repository;

import com.example.demo.entity.BuyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Xiang Li
 * @date created in 2018/10/18 12:49
 * @since 1.0.0
 */
//Dao层必须定义为接口，类无法继承JpaRepository
//使用Spring Data JPA建立数据访问层，定义继承JpaRespository的接口<table,id>
public interface BuyRepository extends JpaRepository<BuyDTO,Integer> {


    //@Param()注解单一属性
    //Page是Spring Data提供的一个接口，该接口表示一部分数据的集合以及其相关的下一部分数据、数据总数等相关信息
    //Pageable 是Spring封装的分页实现类，使用的时候需要传入页数、每页条数和排序规则。
    Page<BuyDTO> findAllByUsername(@Param("username")String username, Pageable pageable);

    Page<BuyDTO> findAllByStateAndCommodityIn(@Param("state")String state,@Param("commodity")List<String> commodity,Pageable pageable);
}

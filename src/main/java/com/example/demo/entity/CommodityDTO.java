package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author haocunli
 * @date created in 2018/10/20 17:45
 * @since 1.0.0
 */
@Data
@Table
@Entity
public class CommodityDTO {
    @Id
    private String id;
    private String name;
    private String username;
    private String picture;
    private String category;
    private String introduction;
    private Double price;
    private Integer lave;
    private Integer sell;

}
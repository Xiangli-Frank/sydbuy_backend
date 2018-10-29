package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Xiangli
 * @date created in 2018/10/17 09:02
 * @since 1.0.0
 */

//将常规的普通Java对象映射到数据库


//减少get set的使用
@Data
//定义表
@Table
//定义BuyDTO为一个是实体类，默认对应数据库中的表名是ComentDTO
@Entity
public class BuyDTO {
    //根据底层数据库自动选择的方式，需要底层数据库的设置
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;// order id
    private String commodity;//关联商品id commodity_id
    private String name;//商品名称 commodity_name
    private String username;//买家 buyer
    private String picture;//图片 commodity_picture
    private String content;//简介 introduction
    private Double price;//价格 price
    private Integer count;//购买数量 num
    private String state;//购买状态 oder state  order：提交订单  success：成功交易  fail：失败交易
}

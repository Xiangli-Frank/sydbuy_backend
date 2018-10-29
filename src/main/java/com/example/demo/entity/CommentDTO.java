package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Lina Fu
 * @date created in 2018/10/17 08:59
 * @since 1.0.0
 */

//将常规的普通Java对象映射到数据库
//减少get set的使用
@Data
//定义表
@Table
//定义CommentDTO为一个是实体类，默认对应数据库中的表名是ComentDTO
@Entity
public class CommentDTO {
    //根据底层数据库自动选择的方式，需要底层数据库的设置
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String commodity;//关联商品id
    private String username;//用户名
    private String comment;//评论内容
    private Date date;//评论日期
}

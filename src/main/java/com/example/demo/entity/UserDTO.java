package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



//将常规的普通Java对象映射到数据库
//减少get set的使用
@Data
//定义表
@Table
//定义CommentDTO为一个是实体类，默认对应数据库中的表名是ComentDTO
@Entity
public class UserDTO {
    @Id
    private String username;//用户名
    private String password;//密码
    private String role;//角色
}

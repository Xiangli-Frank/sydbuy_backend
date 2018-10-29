package com.example.demo.controller;

import com.example.demo.entity.CommentDTO;
import com.example.demo.enums.RestStatusEnum;
import com.example.demo.model.RestEntity;
import com.example.demo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Lina Fu
 * @date created in 2018/10/19 14:12
 * @since 1.0.0
 */

//@Controller表明该类所有方法默认返回页面路径，加了@ResponseBody的方法返回数据。@RestController返回的都是数据，不返回页面
@RestController
public class CommentController {

    //@Autowired 注入类对类成员变量，方法和构造函数标注，自动装配，消除set,get
    @Autowired
    CommentRepository commentRepository;
    //上传数据，将数据发给服务器

    @PostMapping("/addComment")
    //@RequestBody 接受的是一个Json对象的字符串，不是一个Json对象
    public RestEntity addComment(@RequestBody CommentDTO commentDTO){
        commentDTO.setDate(new Date());
        this.commentRepository.save(commentDTO);
        return RestEntity.ok(RestStatusEnum.OK);
    }
}

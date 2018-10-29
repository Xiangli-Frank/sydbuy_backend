package com.example.demo.model;

import com.example.demo.enums.RestStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * @author Xiang li
 * @date created in 2018/10/24 23:42
 * @since 1.0.0
 */
//封装相应内容，原始的做法可以在每个Controller中去处理，返回数据本身就封装有处理码，数据，异常。不好就是每个Controller都需要向外暴露一个返回的对象封装处理
//最好同一处理这种转换，在无异常时返回数据本身，出现异常时显示出合适的异常


@Data
//生成tostring方法
@ToString
//属性值为null的不参加序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
//忽略位置属性的jason转化，防止一些Json转换异常错误
@JsonIgnoreProperties(ignoreUnknown = true)

//实现对象的持久化，将对象写入磁盘，在程序调用时重新恢复对象，实现持久
public class RestEntity implements Serializable {
    //保持版本的兼容性，在版本升级反序列化仍然保持对象的唯一性
    private static final long serialVersionUID = 3550224421750657701L;

    /**
     * [M] condition code
     */
    @JsonProperty("code")
    private Integer code;

    /**
     * [M] error message
     */
    @JsonProperty("msg")
    private String msg;

    /**
     * [C] username
     */
    @JsonProperty("username")
    private String username;

    /**
     * [C] role
     */
    @JsonProperty("role")
    private String role;

    /**
     * [C] details
     */
    @JsonProperty("details")
    private Object details;

    public static RestEntity ok(RestStatusEnum restStatusEnum, @Null String username, @Null String role){
        return new RestEntity(restStatusEnum,username,role);
    }
    public static RestEntity ok(Object details){
        return new RestEntity(RestStatusEnum.OK,details);
    }
    public static RestEntity error(RestStatusEnum restStatusEnum, @Null Object error){
        return new RestEntity(restStatusEnum,error);
    }
	
    public RestEntity(RestStatusEnum restStatusEnum, @Null String username,@Null String role) {
        this.code = restStatusEnum.getCode();
        this.msg = restStatusEnum.getMessage();
        if (username != null) {
            this.username = username;
        }
        if (role != null) {
            this.role = role;
        }
    }

    public RestEntity(RestStatusEnum restStatusEnum, @Null Object details) {
        this.code = restStatusEnum.getCode();
        this.msg = restStatusEnum.getMessage();
        if (details != null) {
            this.details = details;
        }

    }

}
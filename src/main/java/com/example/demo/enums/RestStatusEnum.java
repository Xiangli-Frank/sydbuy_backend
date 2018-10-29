package com.example.demo.enums;

/**
 * API 统一返回状态码
 */
public enum RestStatusEnum {
    /**
     * 凭证错误
     */
    INVALID_CREDENTIAL(1001, "wrong user name or password！"),//用户名或密码错误
    ACCOUNT_NOT_EXIST(1004, "User does not exist!"),//用户不存在
    ACCOUNT_EXISTED(1005, "The user already exists, please log in directly!"),//用户已存在,请直接登陆！
    ACCOUNT_REGISTER_SUCCESS(1006, "User registration is successful！"),//用户注册成功
    ACCOUNT_REGISTER_FAIL(1007, "User registration failed！"),//用户注册失败
    ACCOUNT_LOGIN_SUCCESS(1008, "User login succeeded！"),//用户登陆成功
    ACCOUNT_LOGOUT_SUCCESS(1009, "User logout succeeded！"),//用户退出成功
    OK(2000, "OK"),
    RELEASE_SUCCESS(2001, "Products released successfully！"),
    UPLOAD_IMAGE__ERROR(2002, "Image upload error"),
    RELEASE_ERROR(2004, "The product already exists and can not be re-released！"),//该商品已存在，不能重新发布
    // 40xxx 客户端不合法的请求
    INVENTORY_SHORTAGE(2005, "Inventory shortage"),
    /**
     * 参数类型非法，常见于SpringMVC中String无法找到对应的enum而抛出的异常
     */
    INVALID_PARAMS_CONVERSION(2006, "参数类型非法"),
    // 41xxx 请求方式出错
    /**
     * http media type not supported
     */
    HTTP_MESSAGE_NOT_READABLE(2007, "HTTP消息不可读"),
    /**
     * 请求方式非法
     */
    REQUEST_METHOD_NOT_SUPPORTED(2008, "不支持的HTTP请求方法"),

    MEDIA_TYPE_NOT_SUPPORTED(2009, "不支持的MediaType请求方法"),
    /**
     * Duplicate Key
     */
    DUPLICATE_KEY(2010, "操作过快, 请稍后再试"),
    /**
     * 用于处理未知的服务端错误
     */
    SERVER_UNKNOWN_ERROR(2012, "服务端异常, 请稍后再试"),

    PERMISSION_NOT_SUPPORT(2013, "无访问权限");
    private final Integer code;
    private final String message;



    RestStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

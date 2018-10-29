package com.example.demo.enums;

/**
 * @author Xiang Li
 * @date created in 2018/7/23 10:59
 * @since 1.0.0
 */
public enum RoleEnum {
    ADMIN(1,"ROLE_ADMIN"), USER(2,"ROLE_USER"),MERCHANT(3,"ROLE_MERCHANT");
    private final Integer index;
    private final String role;


    RoleEnum(Integer index, String role) {
        this.index = index;
        this.role = role;
    }

    public Integer getIndex() {
        return index;
    }

    public String getRole() {
        return role;
    }
}

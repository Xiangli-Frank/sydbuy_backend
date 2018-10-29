package com.example.demo.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @author XiangLi
 * @date created in 2018/9/26 10:53
 * @since 1.0.0
 */
public class MySQL5DialectUTF8 extends MySQL5InnoDBDialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }
}

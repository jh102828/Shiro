package com.qfedu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-04-14 11:06:42
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 617289138502785533L;
    
    private Integer uid;
    
    private String username;
    
    private String password;
    
    private String tel;
    
    private String addr;
}
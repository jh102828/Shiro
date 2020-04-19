package com.qfedu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-04-14 11:06:42
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 581645870054218482L;
    
    private Integer pid;
    
    private String pname;
    
    private String pdesc;

    private Set<Role> rs;
}
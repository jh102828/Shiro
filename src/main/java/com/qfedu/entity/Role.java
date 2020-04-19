package com.qfedu.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-04-14 11:06:42
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = -74163700661732397L;
    
    private Integer rid;
    
    private String rname;
    
    private String rdesc;

    private Set<Permission> ps;
}
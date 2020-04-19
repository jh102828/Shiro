package com.qfedu.service;

import com.qfedu.entity.Permission;
import com.qfedu.entity.Role;
import com.qfedu.entity.User;

import java.util.List;

public interface IUserService {

    User login(String username, String pass);

    List<Role> getAllRolesByUsername(String username);

    List<Permission> getAllPermissionsByUsername(String username);
}

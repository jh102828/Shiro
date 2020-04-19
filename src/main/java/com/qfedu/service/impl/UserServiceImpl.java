package com.qfedu.service.impl;

import com.qfedu.dao.IUserDao;
import com.qfedu.dao.impl.UserDaoImpl;
import com.qfedu.entity.Permission;
import com.qfedu.entity.Role;
import com.qfedu.entity.User;
import com.qfedu.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private IUserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String pass) {
        return userDao.login(username, pass);
    }

    @Override
    public List<Role> getAllRolesByUsername(String username) {
        return userDao.getAllRolesByUsername(username);
    }

    @Override
    public List<Permission> getAllPermissionsByUsername(String username) {
        return userDao.getAllPermissionsByUsername(username);
    }
}

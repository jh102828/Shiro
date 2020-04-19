package com.qfedu.dao.impl;

import com.qfedu.dao.IUserDao;
import com.qfedu.entity.Permission;
import com.qfedu.entity.Role;
import com.qfedu.entity.User;
import com.qfedu.util.C3P0Utils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    private Connection conn = null;
    private PreparedStatement ptst = null;
    private ResultSet rs = null;


    @Override
    public User login(String username, String pass) {

        User u = null;

        try {
            conn = C3P0Utils.getConnection();

            String sql = "select * from user where username = ? and password = ?";

            ptst = conn.prepareStatement(sql);

            ptst.setString(1, username);
            ptst.setString(2, pass);

            rs = ptst.executeQuery();

            if(rs.next()){
                u = new User();

                u.setUid(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setTel(rs.getString(4));
                u.setAddr(rs.getString(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Utils.closeAll(conn, ptst, rs);
        }

        return u;
    }

    @Override
    public List<Role> getAllRolesByUsername(String username) {
        List<Role> list = null;

        try {
            conn = C3P0Utils.getConnection();

            String sql = "SELECT r.* \n" +
                    "\tFROM `user` u\n" +
                    "\tINNER JOIN user_role ur on u.uid = ur.uid\n" +
                    "\tINNER JOIN role r on ur.rid = r.rid\n" +
                    "\twhere u.username = ? ";

            ptst = conn.prepareStatement(sql);

            ptst.setString(1, username);

            rs = ptst.executeQuery();

            if(rs != null){
                list = new ArrayList<>();

                Role r = null;
                while (rs.next()){
                    r = new Role();

                    r.setRid(rs.getInt(1));
                    r.setRname(rs.getString(2));
                    r.setRdesc(rs.getString(3));

                    list.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Utils.closeAll(conn, ptst, rs);
        }

        return list;
    }

    @Override
    public List<Permission> getAllPermissionsByUsername(String username) {
        List<Permission> list = null;

        try {
            conn = C3P0Utils.getConnection();

            String sql = "SELECT p.* \n" +
                    "\tFROM `user` u\n" +
                    "\tINNER JOIN user_role ur on u.uid = ur.uid\n" +
                    "\tINNER JOIN role r on ur.rid = r.rid\n" +
                    "\tINNER JOIN role_perms rp on r.rid = rp.rid\n" +
                    "\tINNER JOIN permission p on rp.pid = p.pid\n" +
                    "\twhere u.username = ?";

            ptst = conn.prepareStatement(sql);

            ptst.setString(1, username);

            rs = ptst.executeQuery();

            if(rs != null){
                list = new ArrayList<>();

                Permission r = null;
                while (rs.next()){
                    r = new Permission();

                    r.setPid(rs.getInt(1));
                    r.setPname(rs.getString(2));
                    r.setPdesc(rs.getString(3));

                    list.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            C3P0Utils.closeAll(conn, ptst, rs);
        }

        return list;
    }

    @Test
    public void testlogin(){
        System.out.println(new UserDaoImpl().login("wukong", "888888"));
    }

    @Test
    public void testGetRoles(){
        System.out.println(new UserDaoImpl().getAllRolesByUsername("wukong"));
    }
}

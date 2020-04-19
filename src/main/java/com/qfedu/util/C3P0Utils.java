package com.qfedu.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class C3P0Utils {

    private static final String DB_URL= Env.getInstance().getProperty("url");
    private static final String DB_DRIVER= Env.getInstance().getProperty("driver");
    private static final String DB_USERNAME= Env.getInstance().getProperty("user");
    private static final String DB_PASSWORD= Env.getInstance().getProperty("pass");

    public static Connection getConnection(){
        Connection conn = null;

        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();

            ds.setDriverClass(DB_DRIVER);
            ds.setJdbcUrl(DB_URL);
            ds.setUser(DB_USERNAME);
            ds.setPassword(DB_PASSWORD);

            conn = ds.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static void closeAll(Connection conn, PreparedStatement ptst, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
                rs = null;
            }
            if(ptst != null){
                ptst.close();
                ptst = null;
            }
            if(conn != null){
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

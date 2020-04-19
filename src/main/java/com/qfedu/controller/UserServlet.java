package com.qfedu.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/UserServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 构建SecurityManager工厂，IniSecurityManagerFactory可以从ini文件中初始化SecurityManager环境
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory();

        // 通过工厂创建SecurityManager
        SecurityManager manager = factory.getInstance();

        // 将SecurityManager设置到运行环境中
        SecurityUtils.setSecurityManager(manager);

        //创建一个Subject实例，该实例认证需要使用上面创建的SecurityManager
        Subject subject = SecurityUtils.getSubject();

        //创建token令牌，账号和密码是ini文件中配置的
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            //用户登录
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        System.out.println(subject.hasRole("manager"));
        System.out.println(subject.hasRole("guest"));

        System.out.println(subject.isPermitted("select"));
        System.out.println(subject.isPermitted("delete"));

        if(subject.isAuthenticated()){
            response.sendRedirect("main.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}

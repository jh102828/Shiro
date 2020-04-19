package com.qfedu.shiro;

import com.qfedu.entity.Permission;
import com.qfedu.entity.Role;
import com.qfedu.entity.User;
import com.qfedu.service.IUserService;
import com.qfedu.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import java.util.List;

public class MyRealm extends AuthorizingRealm {

    FormAuthenticationFilter f;

    private IUserService userService = new UserServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        System.out.println(principals + "000000");

        String username = getAvailablePrincipal(principals).toString();

        List<Role> list = userService.getAllRolesByUsername(username);

        for (Role r : list) {
            info.addRole(r.getRname());
        }

        List<Permission> permissionList = userService.getAllPermissionsByUsername(username);

        for (Permission p : permissionList) {
            info.addStringPermission(p.getPname());
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        AuthenticationInfo info = null;

        UsernamePasswordToken tk = (UsernamePasswordToken) token;

        String username = tk.getUsername();
        char[] password = tk.getPassword();

        String pass = new String(password);

        User u = userService.login(username, pass);

        if (u != null && u.getUid() != 0){
            info = new SimpleAuthenticationInfo(username, pass, getName());
        }

        return info;
    }
}

package com.sicnu.config;


import com.sicnu.mapper.AuthMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.Auth;
import com.sicnu.pojo.Role;
import com.sicnu.pojo.User;
import com.sicnu.service.impl.RoleServiceImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author ：yangchun
 * @date ：2020/11/13 09:04
 * @description：自定义 shiroRealm, 主要是重写其认证、授权
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserMapper iUserService;

    @Resource
    private RoleServiceImpl roleService;

    @Resource
    private AuthMapper authMapper;



    /**
     * create by: yangchun
     * description: 授权
     * create time: 2020/11/13 09:04
     *
     * @return 权限信息，包括角色以及权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.......");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //如果身份认证的时候没有传入User对象，这里只能取到userName
        //也就是SimpleAuthenticationInfo构造的时候第一个参数传递需要User对象
        User user = (User) principals.getPrimaryPrincipal();

        // 查询用户角色，一个用户可能有多个角色
        List<Role> roles = roleService.getRole(user.getRole_id());

        for (Role role : roles) {
            authorizationInfo.addRole(role.getRole_name());
            // 根据角色查询权限
            List<Auth> permissions = authMapper.getAuth(role.getRole_id());
            for (Auth p : permissions) {
                authorizationInfo.addStringPermission(p.getAuth_name());
            }
        }
        return authorizationInfo;
    }

    /**
     * create by: yangchun
     * description: 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     * create time: 2020/11/13
     *
     * @return 身份验证信                                                                                                            息
     */
    @SneakyThrows
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.warn("开始进行身份认证......");

        //获取用户的输入的账号.
        String userName = (String) token.getPrincipal();

        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = iUserService.findByName(userName);
        if (Objects.isNull(user)) {
            return null ;
        }

        System.out.println("aaa"+user.getUser_pwd());
        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getUser_pwd(),
                ByteSource.Util.bytes(user.getUser_act()),
                // realm name
                getName()
        );
    }


}

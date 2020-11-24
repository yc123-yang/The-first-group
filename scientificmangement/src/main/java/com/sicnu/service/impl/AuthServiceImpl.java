package com.sicnu.service.impl;

import com.sicnu.mapper.AuthMapper;
import com.sicnu.pojo.Auth;
import com.sicnu.service.AuthService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    AuthMapper authMapper;

    private Result rs = null;

    @Override
    public Result addAuth(String auth_name) {
        Auth auth = authMapper.selectAuthByName(auth_name);
        if (auth == null) {
            authMapper.addAuth(auth_name);
            rs = new Result("0", "添加权限成功", null);
        } else {
            rs = new Result("1", "该权限已存在", null);
        }
        return rs;
    }

    @Override
    public Result delAuth(Integer auth_id) {
        authMapper.delAuth(auth_id);
        return rs = new Result("0", "删除成功", null);
    }

    @Override
    public Result findAllAuth() {
        List<Auth> auths = authMapper.findAllAuth();
        rs = new Result("0", null, auths);
        return rs;
    }

    @Override
    public List<Object> getAuth(Integer role_id) {
        List<Auth> auths = authMapper.getAuth(role_id);
        List<Object> authList = new ArrayList<>();
        for (Auth auth : auths) {
            authList.add(auth.getAuth_resource());
        }
        return authList;
    }

    @Override
    public Result findAuthByPid(Integer user_id, Integer auth_pid) {
        List<Auth> auths = authMapper.findAuthByPid(user_id, auth_pid);
        return rs = new Result("200", "查找成功", auths);
    }

}

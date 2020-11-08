package com.sicnu.service.impl;

import com.sicnu.mapper.RoleMapper;
import com.sicnu.service.RoleService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    @Override
    public Result addRole(String role_name) {
        Result rs = null;
        roleMapper.addRole(role_name);
        rs = new Result("0","添加成功",null);
        return rs;
    }

    @Override
    public Result delRole(Integer role_id) {
        Result rs = null;
        roleMapper.delRole(role_id);
        rs = new Result("0","删除成功",null);
        return rs;
    }
}

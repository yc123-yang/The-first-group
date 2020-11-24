package com.sicnu.service.impl;

import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.Role;
import com.sicnu.service.RoleService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    private Result rs = null;

    @Override
    public Result addRole(String role_name) {
        Role role = roleMapper.selectRoleByName(role_name);
        if (role == null) {
            roleMapper.addRole(role_name);
            rs = new Result("0", "添加成功", null);
        } else {
            rs = new Result("1", "该角色已存在", null);
        }
        return rs;
    }

    @Override
    public Result delRole(Integer role_id) {

        roleMapper.delRole(role_id);
        rs = new Result("0", "删除成功", null);
        return rs;
    }

    @Override
    public Result findAllRole() {
        List<Role> roles = roleMapper.findAllRole();
        rs = new Result("0", null, roles);
        return rs;
    }

    @Override
    public List<Role> getRole(Integer role_id) {
        List<Role> roles = roleMapper.getRole(role_id);
        rs = new Result("0", null, roles);
        return roles;
    }


}

package com.sicnu.mapper;

import com.sicnu.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {
    void addRole(String role_name);

    void delRole(Integer role_id);

    List<Role> findAllRole();

    Role selectRoleByName(String role_name);

    List<Role> getRole(Integer role_id);
    String selectRoleName(Integer role_id);
}

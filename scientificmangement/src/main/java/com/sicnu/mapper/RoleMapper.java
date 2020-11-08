package com.sicnu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RoleMapper {
    void addRole(String role_name);
    void delRole(Integer role_id);
}

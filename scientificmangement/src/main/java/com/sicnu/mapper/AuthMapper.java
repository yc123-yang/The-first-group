package com.sicnu.mapper;

import com.sicnu.pojo.Auth;
import com.sicnu.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AuthMapper {
    void addAuth(String auth_name);
    void delAuth(Integer auth_id);
    List<Auth> findAllAuth();
    Auth selectAuthByName(String auth_name);
    List<Auth> getAuth(Integer role_id);
}


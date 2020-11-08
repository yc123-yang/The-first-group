package com.sicnu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AuthMapper {
    void addAuth(String auth_name);
    void delAuth(Integer auth_id);
}


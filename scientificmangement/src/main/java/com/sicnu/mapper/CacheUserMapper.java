package com.sicnu.mapper;

import com.sicnu.pojo.CacheUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CacheUserMapper {

    void addCacheUser(CacheUser cacheUser);

    List<CacheUser> findAllCacheUser();

    void delCacheUser();
}

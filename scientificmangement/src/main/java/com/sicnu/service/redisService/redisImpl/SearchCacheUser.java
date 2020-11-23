package com.sicnu.service.redisService.redisImpl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.service.redisService.SearchCacheUserService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchCacheUser implements SearchCacheUserService {

    @Autowired
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    CacheUserMapper cacheUserMapper;

    public List searchCacheUser(){
        List list = new ArrayList<>();
        if (redisUtil.hasKey("cacheUserList")) {
            log.warn("从redis中获取数据.");
            list = redisList.get("cacheUserList", 0, -1);
           } else {
            list = cacheUserMapper.findAllCacheUser();
            log.warn("从数据库中获取数据.");
            log.warn("将数据存入redis...");
            redisList.set("cacheUserList", list);
            log.info("成功存入redis.");
            }
        return list;
    }
}

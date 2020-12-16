package com.sicnu.service.impl;


import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.PublicationPlaceMapper;
import com.sicnu.pojo.PublicationPlace;
import com.sicnu.service.PublicationPlaceService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PublicationPlaceImpl implements PublicationPlaceService {

    @Resource
    PublicationPlaceMapper publicationPlaceMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;

    @Override
    public Result addPublicationPlace(String pp_name) {
        try {
            PublicationPlace publicationPlace = publicationPlaceMapper.findPublicationPlaceByName(pp_name);
            if (publicationPlace==null){
                publicationPlaceMapper.addPublicationPlace(pp_name);
                rs = new Result("200","插入成功",null);
            }else{
                rs = new Result("400","该字典数据已存在，切勿重复添加",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPublicationPlace(Integer pp_id) {

        try {
            publicationPlaceMapper.delPublicationPlace(pp_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPublicationPlace() {
        try {
            List publicationPlaces = publicationPlaceMapper.findAllPublicationPlace();
            if (redisUtil.hasKey("publicationPlaces")) {
                log.warn("从redis中获取数据.");
                publicationPlaces = redisList.get("publicationPlaces", 0, -1);
            } else {
                publicationPlaces = publicationPlaceMapper.findAllPublicationPlace();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("publicationPlaces", publicationPlaces);
                log.info("成功存入redis.");
            }
            rs = new Result("200",null,publicationPlaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePublicationPlace(PublicationPlace publicationPlace) {
        try {
            if (publicationPlace.getPp_name()==null){
                rs = new Result("400","该字典数据不存在，无法更改",null);
            }else{
                publicationPlaceMapper.updatePublicationPlace(publicationPlace);
                rs = new Result("200","该字典数据更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

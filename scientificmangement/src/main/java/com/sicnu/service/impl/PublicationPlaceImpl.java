package com.sicnu.service.impl;


import com.sicnu.mapper.PublicationPlaceMapper;
import com.sicnu.pojo.PublicationPlace;
import com.sicnu.service.PublicationPlaceService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PublicationPlaceImpl implements PublicationPlaceService {

    @Resource
    PublicationPlaceMapper publicationPlaceMapper;

    private Result rs;

    @Override
    public Result addPublicationPlace(String pp_name) {
        try {
            PublicationPlace publicationPlace = publicationPlaceMapper.findPublicationPlaceByName(pp_name);
            if (publicationPlace.equals("")){
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
            List<PublicationPlace> publicationPlaces = publicationPlaceMapper.findAllPublicationPlace();
            rs = new Result("200",null,publicationPlaces);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePublicationPlace(PublicationPlace publicationPlace) {
        try {
            PublicationPlace publicationPlace1 = publicationPlaceMapper.findPublicationPlaceByName(publicationPlace.getPp_name());
            if (publicationPlace1.equals("")){
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

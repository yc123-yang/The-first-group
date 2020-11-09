package com.sicnu.service.impl;

import com.sicnu.mapper.NatureMapper;
import com.sicnu.pojo.Nature;
import com.sicnu.service.NatureService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NatureServiceImpl implements NatureService {
    @Resource
    NatureMapper natureMapper;
    private Result rs;
    @Override
    public Result addNature(String nature_name) {
        Nature nature = natureMapper.selectNatureByName(nature_name);
        if (nature==null){
            rs = new Result("0","添加成功",null);
        }
        return rs;
    }

    @Override
    public Result delNature(Integer nature_id) {
        natureMapper.delNature(nature_id);
        rs = new Result("0","删除成功",null);
        return rs;
    }

    @Override
    public Result findAllNature() {
        List<Nature> natures = natureMapper.findAllNature();
        rs = new Result("0",null,natures);
        return rs;
    }
}

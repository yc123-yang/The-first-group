package com.sicnu.service.impl;

import com.sicnu.mapper.ConclusionTypeMapper;
import com.sicnu.pojo.ConclusionType;
import com.sicnu.service.ConclusionTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
结题
 */
@Service
public class ConclusionTypeServiceImpl implements ConclusionTypeService {
    @Resource
    ConclusionTypeMapper conclusionTypeMapper;

    private Result rs;

    @Override
    public Result addConclusionType(String ct_name) {
        ConclusionType conclusionType = conclusionTypeMapper.selectConclusionTypeByName(ct_name);
        if (conclusionType == null) {
            rs = new Result("0", "添加成功", null);
            conclusionTypeMapper.addConclusionType(ct_name);
        } else {
            rs = new Result("0", "已存在", null);

        }
        return rs;
    }

    @Override
    public Result delConclusionType(Integer ct_id) {
        conclusionTypeMapper.delConclusionType(ct_id);
        rs = new Result("0", "删除成功", null);
        return rs;
    }

    @Override
    public Result findAllConclusionType() {
        List<ConclusionType> conclusionTypes = conclusionTypeMapper.findAllConclusionType();
        rs = new Result("0", null, conclusionTypes);
        return rs;
    }
}

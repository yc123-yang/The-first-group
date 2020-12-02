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
        try {
            ConclusionType conclusionType = conclusionTypeMapper.selectConclusionTypeByName(ct_name);
            if (conclusionType == null) {
                rs = new Result("200", "添加成功", null);
                conclusionTypeMapper.addConclusionType(ct_name);
            } else {
                rs = new Result("400", "该字典已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delConclusionType(Integer ct_id) {
        try {
            conclusionTypeMapper.delConclusionType(ct_id);
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllConclusionType() {
        try {
            List<ConclusionType> conclusionTypes = conclusionTypeMapper.findAllConclusionType();
            rs = new Result("200", null, conclusionTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateConclusionType(ConclusionType conclusionType) {
        try {
            conclusionTypeMapper.updateConclusionType(conclusionType);
            rs = new Result("20","更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

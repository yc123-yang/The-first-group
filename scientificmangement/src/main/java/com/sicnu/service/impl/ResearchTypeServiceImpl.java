package com.sicnu.service.impl;

import com.sicnu.mapper.ResearchTypeMapper;
import com.sicnu.pojo.ResearchType;
import com.sicnu.service.ResearchTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResearchTypeServiceImpl implements ResearchTypeService {

    @Resource
    ResearchTypeMapper researchTypeMapper;

    private Result rs;


    @Override
    public Result addResearchType(String rt_name) {
        try {
            ResearchType researchType = researchTypeMapper.selectResearchTypeByName(rt_name);
            if (researchType==null){
                researchTypeMapper.addResearchType(rt_name);
                rs = new Result("200","字典数据插入成功",null);
            }else{
                rs = new Result("400","字典数据已存在，请勿重复插入",null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delResearchType(Integer rt_id) {
        try {
            researchTypeMapper.delResearchType(rt_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllResearchType() {
        try {
            List<ResearchType> researchTypes = researchTypeMapper.findAllResearchType();
            rs = new Result("200",null,researchTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateResearchType(ResearchType researchType) {
        if (researchType.getRt_name().equals("")){
            rs = new Result("400","字典数据不能为空",null);
        }else{
            researchTypeMapper.updateResearchType(researchType);
            rs = new Result("200","更改字典数据成功",null);
        }
        return rs;
    }
}

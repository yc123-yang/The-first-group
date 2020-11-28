package com.sicnu.service.impl;

import com.sicnu.mapper.PaperTypeMapper;
import com.sicnu.mapper.PatentTypeMapper;
import com.sicnu.pojo.PatentType;
import com.sicnu.service.PatentTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatentTypeServiceImpl implements PatentTypeService {
    @Resource
    PatentTypeMapper patentTypeMapper;

    private Result rs ;
    @Override
    public Result addPatentType(String pt_name) {
        try {
            PatentType patentType = patentTypeMapper.selectPatentTypeByName(pt_name);
            if (patentType==null){
                patentTypeMapper.addPatentType(pt_name);
                rs = new Result("200","插入成功",null);
            }else{
                rs = new Result("400","该字典数据已经存在",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPatentType(Integer pt_id) {
        try {
            patentTypeMapper.delPatentType(pt_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllPatentType() {
        try {
            List<PatentType> patentTypes = patentTypeMapper.findAllPatentType();
            rs = new Result("200",null,patentTypes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePatentType(PatentType patentType) {
        if (patentType.getPt_name().equals("")){
            rs = new Result("400","更改数据不能为空",null);
        }else{
            patentTypeMapper.updatePatentType(patentType);
            rs = new Result("400","更改成功",null);
        }
        return rs;
    }
}

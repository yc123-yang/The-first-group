package com.sicnu.service.impl;

import com.sicnu.mapper.SubjectCategoryMapper;
import com.sicnu.pojo.SubjectCategory;
import com.sicnu.service.SubjectCategoryService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    SubjectCategoryMapper subjectCategoryMapper;

    private Result rs;

    @Override
    public Result addSubjectCategory(String sc_name) {
        SubjectCategory subjectCategory = subjectCategoryMapper.selectSubjectCategoryByName(sc_name);
        if (subjectCategory==null){
            rs =new Result("0","添加学科门类成功",null);
        }else{
            rs =new Result("1","学科门类已经存在",null);

        }
        return rs;
    }

    @Override
    public Result delSubjectCategory(Integer sc_id) {
        subjectCategoryMapper.delSubjectCategory(sc_id);
        rs =new Result("0","删除学科门类成功",null);
        return rs;
    }

    @Override
    public Result findAllSubjectCategory() {
        List<SubjectCategory> subjectCategories = subjectCategoryMapper.findAllSubjectCategory();
        rs =new Result("0",null,subjectCategories);
        return rs;
    }


}

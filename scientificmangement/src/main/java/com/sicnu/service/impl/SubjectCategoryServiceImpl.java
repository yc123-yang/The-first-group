package com.sicnu.service.impl;

import com.sicnu.mapper.SubjectCategoryMapper;
import com.sicnu.pojo.SubjectCategory;
import com.sicnu.service.SubjectCategoryService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学科门类
 */
@Service
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    SubjectCategoryMapper subjectCategoryMapper;

    private Result rs;

    @Override
    public Result addSubjectCategory(String sc_id, String sc_name) {
        try {
            SubjectCategory subjectCategory = subjectCategoryMapper.selectSubjectCategoryByName(sc_name);
            if (subjectCategory == null) {
                subjectCategoryMapper.addSubjectCategory(sc_id, sc_name);
                rs = new Result("200", "添加学科门类成功", null);

            } else {
                rs = new Result("400", "学科门类已经存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delSubjectCategory(String sc_id) {
        try {
            subjectCategoryMapper.delSubjectCategory(sc_id);
            rs = new Result("200", "删除学科门类成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllSubjectCategory() {
        try {
            List<SubjectCategory> subjectCategories = subjectCategoryMapper.findAllSubjectCategory();
            rs = new Result("200", null, subjectCategories);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectSubjectCategory(String sc_id) {
        try {
            List<SubjectCategory> subjects = subjectCategoryMapper.selectSubjectCategory(sc_id);
            List<Object> list = new ArrayList<>();
            for (SubjectCategory subjectCategory : subjects) {
                Map<String, Object> map = new HashMap<>();
                map.put("sc_name", subjectCategory.getSc_name());
                map.put("subject_name", subjectCategory.getSubjects().get(0).getSubject_name());
                list.add(map);
            }
            System.out.println(subjects.get(0).getSc_id());
            rs = new Result("200", null, list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}

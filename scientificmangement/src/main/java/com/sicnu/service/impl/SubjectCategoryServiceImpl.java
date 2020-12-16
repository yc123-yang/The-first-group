package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.SubjectCategoryMapper;
import com.sicnu.pojo.SubjectCategory;
import com.sicnu.service.SubjectCategoryService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    SubjectCategoryMapper subjectCategoryMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    private Result rs;

    @Override
    public Result addSubjectCategory(String sc_id, String sc_name) {
        try {
            SubjectCategory subjectCategory = subjectCategoryMapper.selectSubjectCategoryByName(sc_name);
            if (subjectCategory == null) {
                subjectCategoryMapper.addSubjectCategory(sc_id, sc_name);
                redisUtil.del("subjectCategories");
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
            redisUtil.del("subjectCategories");
            rs = new Result("200", "删除学科门类成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllSubjectCategory() {
        try {
            List subjectCategories = new ArrayList<>();
            if (redisUtil.hasKey("subjects")) {
                log.warn("从redis中获取数据.");
                subjectCategories = redisList.get("subjectCategories", 0, -1);
            } else {
                subjectCategories = subjectCategoryMapper.findAllSubjectCategory();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("subjectCategories", subjectCategories);
                log.info("成功存入redis.");
            }
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

    @Override
    public Result updateSubjectCategory(SubjectCategory subjectCategory) {
        try {
            subjectCategoryMapper.updateSubjectCategory(subjectCategory);
            redisUtil.del("subjectCategories");
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}

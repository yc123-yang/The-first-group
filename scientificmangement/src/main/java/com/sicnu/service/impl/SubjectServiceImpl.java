package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.SubjectMapper;
import com.sicnu.pojo.Subject;
import com.sicnu.service.SubjectService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 一级学科
 */
@Service
@Slf4j
public class SubjectServiceImpl implements SubjectService {
    private Result rs;
    @Resource
    SubjectMapper subjectMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Result addSubject(String subject_id, String subject_name, String sc_id) {
        try {
            Subject subject = subjectMapper.selectSubjectByName(subject_name);
            if (subject == null) {
                subjectMapper.addSubject(subject_id, subject_name, sc_id);
                redisUtil.del("subjects");
                rs = new Result("200", "添加一级学科成功", null);

            } else {
                rs = new Result("400", "C", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delSubject(String subject_id) {
        try {
            subjectMapper.delSubject(subject_id);
            redisUtil.del("subjects");
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result findAllSubject() {
        try {
            List subjects = new ArrayList();
            if (redisUtil.hasKey("subjects")) {
                log.warn("从redis中获取数据.");
                subjects = redisList.get("subjects", 0, -1);
            } else {
                subjects = subjectMapper.findAllSubject();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("subjects", subjects);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, subjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateSubject(Subject subject) {
        try {
            subjectMapper.updateSubject(subject);
            redisUtil.del("subjects");
            rs = new Result("200", "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }


}

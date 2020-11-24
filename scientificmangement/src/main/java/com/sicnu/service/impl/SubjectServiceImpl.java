package com.sicnu.service.impl;

import com.sicnu.mapper.SubjectMapper;
import com.sicnu.pojo.Subject;
import com.sicnu.service.SubjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一级学科
 */
@Service
public class SubjectServiceImpl implements SubjectService {
    private Result rs;
    @Resource
    SubjectMapper subjectMapper;

    @Override
    public Result addSubject(String subject_id, String subject_name, String sc_id) {
        Subject subject = subjectMapper.selectSubjectByName(subject_name);
        if (subject == null) {
            subjectMapper.addSubject(subject_id, subject_name, sc_id);
            rs = new Result("0", "添加一级学科成功", null);

        } else {
            rs = new Result("1", "C", null);
        }
        return rs;
    }

    @Override
    public Result delSubject(String subject_id) {
        subjectMapper.delSubject(subject_id);
        rs = new Result("0", "删除成功", null);

        return rs;
    }

    @Override
    public Result findAllSubject() {
        List<Subject> subjects = subjectMapper.findAllSubject();
        rs = new Result("0", null, subjects);
        return rs;
    }


}

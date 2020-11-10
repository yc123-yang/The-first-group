package com.sicnu.service.impl;

import com.sicnu.mapper.SubjectMapper;
import com.sicnu.pojo.Subject;
import com.sicnu.service.SubjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    private Result rs;
    @Resource
    SubjectMapper subjectMapper;
    @Override
    public Result addSubject(String subject_name) {
        Subject subject = subjectMapper.selectSubjectByName(subject_name);
        if (subject==null){
            subjectMapper.addSubject(subject_name);
            rs = new Result("200","添加成功",null);

        }else{
            rs = new Result("1","删除成功",null);
        }
        return rs;
    }

    @Override
    public Result delSubject(Integer subject_id) {
        subjectMapper.delSubject(subject_id);
        rs = new Result("0","删除成功",null);

        return rs;
    }

    @Override
    public Result findAllSubject() {
        List<Subject>  subjects= subjectMapper.findAllSubject();
        rs = new Result("0",null,subjects);
        return rs;
    }

}

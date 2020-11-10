package com.sicnu.controller;

import com.sicnu.mapper.SubjectMapper;
import com.sicnu.pojo.Subject;
import com.sicnu.service.impl.SubjectServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class SubjectController {
    private Result rs;
    @Resource
    SubjectServiceImpl subjectService;

    @PostMapping("/subject/addSubject")
    public Result addSubject(String subject_name) {

        rs = subjectService.addSubject(subject_name);
        return rs;
    }

    @PostMapping("/subject/delSubject")
    public Result delSubject(Integer subject_id) {
        rs =  subjectService.delSubject(subject_id);
        return rs;
    }

    @PostMapping("/subject/findAllSubject")
    public Result findAllSubject() {
        rs =subjectService.findAllSubject() ;
        return rs;
    }

}

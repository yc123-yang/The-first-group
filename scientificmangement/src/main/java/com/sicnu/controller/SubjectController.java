package com.sicnu.controller;

import com.sicnu.pojo.Subject;
import com.sicnu.service.impl.SubjectServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class SubjectController {
    private Result rs;
    @Resource
    SubjectServiceImpl subjectService;

    @PostMapping("/subject/addSubject")
    @RequiresPermissions("/data")
    public Result addSubject(String subject_id, String subject_name, String sc_id) {
        try {
            rs = subjectService.addSubject(subject_id, subject_name, sc_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/subject/delSubject")
    @RequiresPermissions("/data")
    public Result delSubject(String subject_id) {
        try {
            rs = subjectService.delSubject(subject_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/subject/findAllSubject")
    public Result findAllSubject() {
        try {
            rs = subjectService.findAllSubject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/subject/updateSubject")
    @RequiresPermissions("/data")
    public Result updateSubject(Subject subject) {
        try {
            rs=subjectService.updateSubject(subject);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}

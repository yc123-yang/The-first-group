package com.sicnu.service.impl;

import com.sicnu.dao.CheckProjectDao;
import com.sicnu.pojo.CheckProject;
import com.sicnu.service.CheckProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CheckProjectServiceImpl implements CheckProjectService {

    @Resource
    CheckProjectDao check_projectDao;


    @Override
    public Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) {
        Result rs = null;
        check_projectDao.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        return  rs = new Result("0","项目申请已经上传,请等待审核审核",null);
    }

    @Override
    public void delCheckProjectById(Integer project_id) {
        check_projectDao.delCheckProjectById(project_id);
    }


    @Override
    public Result findAllProject() {
        Result rs =null;
        List<CheckProject> projects = check_projectDao.findAllProject();
        return  rs = new Result("0",null,projects);
    }




}

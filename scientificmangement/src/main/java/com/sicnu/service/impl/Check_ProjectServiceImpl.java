package com.sicnu.service.impl;

import com.sicnu.dao.Check_ProjectDao;
import com.sicnu.dao.ProjectDao;
import com.sicnu.pojo.Check_Project;
import com.sicnu.pojo.Project;
import com.sicnu.service.Check_ProjectService;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class Check_ProjectServiceImpl implements Check_ProjectService {

    @Resource
    Check_ProjectDao check_projectDao;


    @Override
    public Result checkProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) {
        Result rs = null;
        check_projectDao.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        return  rs = new Result("0","项目申请已经上传,请等待审核审核",null);
    }


    @Override
    public Result findAllProject() {
        Result rs =null;
        List<Check_Project> projects = check_projectDao.findAllProject();
        return  rs = new Result("0",null,projects);
    }




}

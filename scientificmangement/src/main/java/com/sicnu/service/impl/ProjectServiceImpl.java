package com.sicnu.service.impl;

import com.sicnu.dao.ProjectDao;
import com.sicnu.pojo.Project;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectDao projectDao;
    @Override
    public Result selectProjectTeamByName(String project_name) {
        Result rs =null;
        List<Project> project = projectDao.selectProjectTeamByName(project_name);
        if (project!=null){
            rs = new Result("0","查找成功",project);
        }else{
            rs = new Result("0","项目未注册申报或已被删除",null);
        }
        return rs;
    }


    @Override
    public Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) {
        Result rs = null;
        projectDao.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
        rs = new Result("0","审核通过",null);
        return rs;
    }

    @Override
    public Result findAllProject() {
        Result rs =null;
        List<Project> projects = projectDao.findAllProject();
        return  rs = new Result("0",null,projects);
    }


    @Override
    public Result updateProjectStage(String project_stage,Integer project_id,String project_name) {
        Result rs =null;
        projectDao.updateProjectStage(project_stage, project_id, project_name);
        return  rs = new Result("0","更改成功",null);


    }

    @Override
    public Result updateProjectType(String project_type,Integer project_id,String project_name) {
        Result rs =null;
        projectDao.updateProjectType(project_type, project_id, project_name);
        return  rs = new Result("0","更改成功",null);
    }

    @Override
    public Result delProject(Integer project_id,Integer leader_id,String project_name) {
        Result rs = null;
        projectDao.delProject(project_id, leader_id, project_name);
        return rs = new Result("0","删除成功",null);
    }

    @Override
    public Result updateProject(Integer leader_id, String project_name, String project_stage, String project_type, Integer project_id) {
        Result rs =null;
        projectDao.updateProject(leader_id, project_name, project_stage, project_type, project_id);
        return  rs = new Result("0","更改成功",null);
    }


}

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
    public Result selectProjectById(String project_name) {
        List<Project> project = projectDao.selectProjectById(project_name);
        return null;
    }

    @Override
    public Result addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time) {
        Result rs = null;
        Project project = new Project();
        project.setProject_stage(project_stage);
        project.setProject_type(project_type);
        project.setLeader_id(leader_id);
        project.setProject_name(project_name);
        project.setProject_abstract(project_abstract);
        project.setDeclaration(declaration);
        return  rs = new Result("0","项目已经上传审核",project);
//        projectDao.addProject(project_stage, project_type, leader_id, project_name, project_abstract, declaration, apply_time);
    }

    @Override
    public Result findAllProject() {
        Result rs =null;
        List<Project> projects = projectDao.findAllProject();
        return  rs = new Result("0",null,projects);
    }

    @Override
    public Result updateProjectStage(String project_stage) {
        Result rs =null;
        projectDao.updateProjectStage(project_stage);
        return  rs = new Result("0","更改成功",null);


    }

    @Override
    public Result updateProjectType(String project_type) {
        Result rs =null;
        projectDao.updateProjectType(project_type);
        return  rs = new Result("0","更改成功",null);
    }

    @Override
    public Result delProject(Integer project_id) {
        Result rs = null;
        projectDao.delProject(project_id);
        return rs = new Result("0","删除成功",null);
    }

    @Override
    public Result updateProject(Integer leader_id, String project_name, String project_stage, String project_type, Integer project_id) {
        Result rs =null;
        projectDao.updateProject(leader_id, project_name, project_stage, project_type, project_id);
        return  rs = new Result("0","更改成功",null);
    }

}

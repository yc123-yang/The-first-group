package com.sicnu.service.impl;

import com.sicnu.dao.ProjectDao;
import com.sicnu.pojo.Project;
import com.sicnu.service.ProjectService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}

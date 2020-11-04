package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.util.Result;

import javax.mail.MessagingException;
import java.util.List;

public interface ProjectService {
    Result addProject(Project project) throws MessagingException;
    Result selectProject(Project project);
    Result delProject(Integer project_id);
    Result updateProject(Project project);
}

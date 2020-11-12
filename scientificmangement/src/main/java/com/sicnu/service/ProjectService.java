package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.util.Result;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface ProjectService {
    Result addProject(Project project,String checkMessage, String message) throws MessagingException;
    Result selectProject(Project project,String start_time_start, String start_time_end, String complete_time_start, String complete_time_end) throws Exception;
    Result delProject(Integer project_id);
    Result updateProject(Project project);
    Result selectTeamByPid(Integer project_Id);
    Result findAllProject();
}

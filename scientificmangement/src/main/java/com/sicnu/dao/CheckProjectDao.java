package com.sicnu.dao;

import com.sicnu.pojo.CheckProject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface CheckProjectDao {
    void addProject(String project_stage, String project_type, Integer leader_id, String project_name, String project_abstract, String declaration, Date apply_time);

    List<CheckProject> findAllProject();
    void delCheckProjectById(Integer project_id);
}

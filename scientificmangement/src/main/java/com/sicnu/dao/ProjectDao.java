package com.sicnu.dao;

import com.sicnu.pojo.Project;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectDao {
    List<Project> selectProjectById(String project_name);
}

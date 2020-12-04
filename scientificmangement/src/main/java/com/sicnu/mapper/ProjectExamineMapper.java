package com.sicnu.mapper;

import com.sicnu.pojo.ProjectExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProjectExamineMapper {
    void addProjectExamine(ProjectExamine projectExamine);
    List<ProjectExamine> selectProjectExamineByCondition(Map<String, Object> map);
    void delProjectExamine(Integer leader_id,String project_name);
    Integer selectTotalProjectExamine(Map<String,Object> map);
    void delProjectExamineById(Integer pe_id);
    Integer selectProjectExamineId(Integer leader_id,String project_name);
    ProjectExamine findProjectExamineById(Integer pe_id);
}

package com.sicnu.mapper;

import com.sicnu.pojo.ProjectExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import sun.util.locale.LocaleSyntaxException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface ProjectExamineMapper {
    void addProjectExamine(ProjectExamine projectExamine);
    List<ProjectExamine> selectProjectExamineByCondition(Map<String, Object> map);
    void delProjectExamine(Integer pe_id);
    Integer selectTotalProjectExamine(Map<String,Object> map);
}

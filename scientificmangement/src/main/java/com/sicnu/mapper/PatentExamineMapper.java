package com.sicnu.mapper;

import com.sicnu.pojo.PatentExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PatentExamineMapper {

    void addPatentExamine(PatentExamine patentExamine);
    List<PatentExamine> selectPatentExamineByCondition(Map<String,Object> map);
    void delPatentExamine(Integer leader_id,String patent_name);
    Integer selectTotalPatentExamine(Map<String,Object> map);
    void delPatentExamineById(Integer pe_id);
    Integer selectPatentExamineId(Integer leader_id,String patent_name);
    PatentExamine findPatentExamineById(Integer pe_id);
}

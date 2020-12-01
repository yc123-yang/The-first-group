package com.sicnu.mapper;

import com.sicnu.pojo.PaperExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PaperExamineMapper {
    void addPaperExamine(PaperExamine paperExamine);
    List<PaperExamine> selectPaperExamineByCondition(Map<String,Object> map);
    void delPaperExamine(Integer leader_id,String paper_name);
    Integer selectTotalPaperExamine(Map<String,Object> map);
    void delPaperExamineById(Integer pe_id);
}

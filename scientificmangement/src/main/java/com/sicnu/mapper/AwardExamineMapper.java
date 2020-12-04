package com.sicnu.mapper;

import com.sicnu.pojo.AwardExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AwardExamineMapper {
    void addAwardExamine(AwardExamine awardExamine);
    List<AwardExamine> selectAwardExamineByCondition(Map<String,Object> map);
    void delAwardExamineById(Integer ae_id);
    void delAwardExamine(Integer leader_id,String award_name);
    Integer selectTotalAwardExamine(Map<String,Object> map);
    Integer selectAwardExamineId(String award_name,Integer leader_id);
    AwardExamine findAwardExamineById(Integer ae_id);
}

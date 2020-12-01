package com.sicnu.mapper;

import com.sicnu.pojo.Award;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AwardMapper {
    void addAward(Award award);

    Award selectAwardByNumber(String issuing_authority);

    List<Award> selectAwardByCondition(Map<String, Object> map);

    void updateAward(Award award);

    void delAward(Integer award_id);

    Integer selectTotalAward(Map<String, Object> map);

    Integer selectCountAward(Map<String, Object> map);

    List<Award> findAwardByLeaderId(Integer leader_id);

    Integer selectAwardId(Integer leader_id,String award_name);
}

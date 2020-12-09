package com.sicnu.mapper;

import com.sicnu.pojo.AwardRank;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AwardRankMapper {
    void addAwardRank(String ar_name,Integer ar_Score);
    void delAwardRank(Integer ar_id);
    List<AwardRank> findAllAwardRank();
    AwardRank selectAwardRankByName(String ar_name);
    void updateAwardRank(AwardRank awardRank);
}

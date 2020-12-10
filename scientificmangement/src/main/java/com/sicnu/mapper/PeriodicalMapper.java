package com.sicnu.mapper;

import com.sicnu.pojo.Periodical;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 期刊
 */
@Mapper
@Repository
public interface PeriodicalMapper {
    void addPeriodical(String periodical_name,Integer pp_score);
    void delPeriodical(Integer periodical_id);
    List<Periodical> findAllPeriodical();
    Periodical selectPeriodicalByName(String periodical_name);
    void updatePeriodical(Periodical periodical);
    Integer selectPeriodicalScoreById(Integer periodical_id);
}

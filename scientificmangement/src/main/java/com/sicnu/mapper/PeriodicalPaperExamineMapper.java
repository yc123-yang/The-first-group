package com.sicnu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PeriodicalPaperExamineMapper {
    void addPeriodicalPaperExamine(Integer pe_id,Integer periodical_id);
    List<Integer> findPeriodicalExamineByPaperId(Integer pe_id);
    void delPeriodicalExamineByPaperId(Integer pe_id);
}

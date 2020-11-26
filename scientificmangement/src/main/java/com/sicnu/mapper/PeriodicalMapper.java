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
    void addPeriodical(String pt_name);
    void delPeriodical(Integer pt_id);
    List<Periodical> findAllPeriodical();
    Periodical selectPeriodicalByName(String pt_name);
}

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
    void addPeriodical();
    void delPeriodical();
    List<Periodical> findAllPeriodical();
    Periodical selectPeriodicalByName();
}

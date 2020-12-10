package com.sicnu.service;

import com.sicnu.pojo.Periodical;
import com.sicnu.util.Result;

import java.util.List;

public interface PeriodicalService {
    Result addPeriodical(String periodical_name,Integer pp_score);
    Result delPeriodical(Integer periodical_d);
    Result findAllPeriodical();
    Result updatePeriodical(Periodical periodical);

}

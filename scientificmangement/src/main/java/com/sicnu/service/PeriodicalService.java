package com.sicnu.service;

import com.sicnu.pojo.Periodical;
import com.sicnu.util.Result;

import java.util.List;

public interface PeriodicalService {
    Result addPeriodical(String pt_name);
    Result delPeriodical(Integer pt_d);
    Result findAllPeriodical();
    Result updatePeriodical(Periodical periodical);

}

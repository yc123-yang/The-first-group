package com.sicnu.service;

import com.sicnu.pojo.Periodical;
import com.sicnu.util.Result;

import java.util.List;

public interface PeriodicalService {
    Result addPeriodical();
    Result delPeriodical();
    Result findAllPeriodical();
}

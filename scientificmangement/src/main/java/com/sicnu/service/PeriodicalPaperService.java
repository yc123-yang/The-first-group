package com.sicnu.service;

import com.sicnu.pojo.PeriodicalPaper;
import com.sicnu.util.Result;

import java.util.List;

public interface PeriodicalPaperService {
    Result  addPeriodicalPaper(Integer paper_id, Integer periodical_id);
    Result  findPeriodicalByPaperId(Integer paper_id);
    Result  delPeriodicalByPaperId(Integer paper_id);
    Result  delPeriodicalByPeriodicalId(Integer periodical_id);
    Result updatePeriodicalPaper(Integer paper_id,Integer[] periodical_ids);

}

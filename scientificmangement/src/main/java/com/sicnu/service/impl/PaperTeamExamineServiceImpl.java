package com.sicnu.service.impl;

import com.sicnu.mapper.PaperTeamExamineMapper;
import com.sicnu.pojo.teamExamine.PaperTeamExamine;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamExamineService;
import com.sicnu.service.PaperTeamService;
import com.sicnu.util.Result;

import javax.annotation.Resource;
import java.util.List;

public class PaperTeamExamineServiceImpl implements PaperTeamExamineService {
    @Resource
    PaperTeamExamineMapper paperTeamExamineMapper;

    private Result rs;
    @Override
    public Result selectPaperTeamExamineUser(Integer pe_id) {
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamExamineMapper.selectPaperTeamExamineUser(pe_id);
            rs = new Result("200",null,paperTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

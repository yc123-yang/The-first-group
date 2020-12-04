package com.sicnu.service.impl;

import com.sicnu.mapper.PaperTeamExamineMapper;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
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

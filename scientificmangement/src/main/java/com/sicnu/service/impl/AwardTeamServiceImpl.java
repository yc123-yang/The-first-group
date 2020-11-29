package com.sicnu.service.impl;

import com.sicnu.mapper.AwardTeamMapper;
import com.sicnu.pojo.teamMap.AwardTeamMap;
import com.sicnu.service.AwardTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AwardTeamServiceImpl implements AwardTeamService {

    @Resource
    AwardTeamMapper awardTeamMapper;
    private Result rs;
    @Override
    public Result addAwardTeamUser(Integer award_id, Integer user_id, String user_role, Double contribution) {
        awardTeamMapper.addAwardTeamUser(award_id, user_id, user_role, contribution);
        rs = new Result("200","添加成功",null);
        return rs;
    }

    @Override
    public Result delAwardTeamUser(Integer user_id) {
        awardTeamMapper.delAwardTeamUser(user_id);
        rs = new Result("200","添加成功",null);
        return rs;

    }

    @Override
    public Result selectAwardTeam(Integer award_id) {
        List<AwardTeamMap> awardTeamMaps = awardTeamMapper.selectAwardTeam(award_id);
        rs = new Result("200",null,awardTeamMaps);
        return rs;
    }
}

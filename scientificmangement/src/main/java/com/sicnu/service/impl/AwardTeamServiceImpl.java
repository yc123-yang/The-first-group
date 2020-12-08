package com.sicnu.service.impl;

import com.sicnu.mapper.AwardTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.AwardTeam;
import com.sicnu.pojo.User;
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
    @Resource
    UserMapper userMapper;

    private Result rs;
    @Override
    public Result addAwardTeamUser(Integer award_id, Integer user_id,  Integer contribution) {
        try {
                awardTeamMapper.addAwardTeamUser(award_id, user_id, contribution);
                rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delAwardTeamUser(Integer award_id,Integer user_id) {
        try {
            awardTeamMapper.delAwardTeamUser(award_id,user_id);
            rs = new Result("200","添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    @Override
    public Result selectAwardTeam(Integer award_id) {
        try {
            List<AwardTeamMap> awardTeamMaps = awardTeamMapper.selectAwardTeam(award_id);
            rs = new Result("200",null,awardTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateAwardTeamUser(AwardTeam awardTeam) {
        try {
            awardTeamMapper.updateAwardTeamUser(awardTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

package com.sicnu.service.impl;

import com.sicnu.mapper.PaperTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.PaperTeam;
import com.sicnu.pojo.User;
import com.sicnu.pojo.teamMap.PaperTeamMap;
import com.sicnu.service.PaperTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PaperTeamServiceImpl implements PaperTeamService {
    @Resource
    PaperTeamMapper paperTeamMapper;
    @Resource
    UserMapper userMapper;
    private Result rs;

    @Override
    public Result addPaperTeamUser(Integer paper_id,Integer user_id,Integer contribution) {
        try {


            paperTeamMapper.addPaperTeamUser(paper_id, user_id, contribution);
            rs = new Result("200","添加作者成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPaperTeamUser(Integer paper_id,Integer user_id) {
        try {
            paperTeamMapper.delPaperTeamUser(paper_id,user_id);
            rs = new Result("200","删除作者成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectPaperTeam(Integer paper_id) {
        try {
            List<PaperTeamMap> paperTeamMaps = paperTeamMapper.selectPaperTeam(paper_id);
            rs = new Result("200",null,paperTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePaperTeamUser(PaperTeam paperTeam) {
        try {
            paperTeamMapper.updatePaperTeamUser(paperTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

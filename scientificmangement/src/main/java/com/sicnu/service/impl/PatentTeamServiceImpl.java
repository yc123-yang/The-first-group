package com.sicnu.service.impl;

import com.sicnu.mapper.PatentTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.User;
import com.sicnu.pojo.teamMap.PatentTeamMap;
import com.sicnu.service.PatentTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PatentTeamServiceImpl implements PatentTeamService {

    @Resource
    PatentTeamMapper patentTeamMapper;

    @Resource
    UserMapper userMapper;
    private Result rs;
    @Override
    public Result addPatentTeamUser(Integer patent_id,String user_name, String user_role, Double contribution,Integer department_id) {
        int user_id = userMapper.findByUserName(user_name);
        User user = userMapper.findUserById(user_id);
        if (user==null){
            rs = new Result("401", "该用户尚未注册信息或名字与注册名字不同", null);
        }else if (!user.getDepartment_id().equals(department_id)) {
            rs = new Result("400", "成员信息有误，无法添加", null);
        } else {
            patentTeamMapper.addPatentTeamUser(patent_id, user_id, user_role, contribution);
            rs = new Result("200","添加成功",null);}
        return rs;
    }

    @Override
    public Result delPatentTeamUser(Integer user_id) {
        patentTeamMapper.delPatentTeamUser(user_id);
        rs = new Result("200","删除成功",null);
        return rs;
    }

    @Override
    public Result selectPatentTeam(Integer patent_id) {
        List<PatentTeamMap> patentTeamMaps= patentTeamMapper.selectPatentTeam(patent_id);
        rs = new Result("200",null,patentTeamMaps);
        return null;
    }
}

package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.ProjectTeamMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.ProjectTeam;
import com.sicnu.pojo.teamMap.ProjectTeamMap;
import com.sicnu.pojo.User;
import com.sicnu.service.ProjectTeamService;

import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 项目组
 */
@Service
public class ProjectTeamServiceImpl implements ProjectTeamService {

    @Resource
    ProjectTeamMapper projectTeamMapper;

    @Resource
    RoleMapper roleMapper;
    private Result rs = null;
    @Override
    public Result addProjectTeamUser(Integer project_id, Integer user_id, String team_role) {
        try {
                ProjectTeam projectTeam = projectTeamMapper.selectProjectId(user_id, project_id);
                if (projectTeam!=null){
                    rs = new Result("400", "该成员已经存在", null);
                }else{
                    projectTeamMapper.addProjectTeamUser(project_id, user_id,team_role);
                    rs = new Result("200", "成员添加成功", null);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delProjectTeamUser(Integer project_id,Integer user_id) {
        try {
            ProjectTeam projectTeam = projectTeamMapper.selectProjectId(user_id, project_id);
            if (projectTeam==null){
                rs = new Result("400", "该成员不存在", null);
            }else{
                projectTeamMapper.delProjectTeamUser(project_id,user_id);
                rs = new Result("200", "删除成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectProjectTeam(Integer project_id) {
        try {
            List<ProjectTeamMap> projectTeamMaps = projectTeamMapper.selectProjectTeam(project_id);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < projectTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(projectTeamMaps.get(i)),Map.class);
                map.put("role_name",roleMapper.selectRoleName(projectTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateProjectTeamUser(ProjectTeam projectTeam) {
        try {
            projectTeamMapper.updateProjectTeamUser(projectTeam);
            rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

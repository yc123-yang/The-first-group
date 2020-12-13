package com.sicnu.service.impl;

import com.alibaba.fastjson.JSON;
import com.sicnu.mapper.BookTeamExamineMapper;
import com.sicnu.mapper.RoleMapper;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.service.BookTeamExamineService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BookTeamExamineServiceImpl implements BookTeamExamineService {
    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;

    private Result rs;

    @Resource
    RoleMapper roleMapper;
    @Override
    public Result selectBookTeamExamineUser(Integer be_id) {
        try {
            List<BookTeamMap> bookTeamMaps = bookTeamExamineMapper.selectBookTeamExamineUser(be_id);
            List<Map> mapList = new ArrayList<>();
            for (int i = 0; i < bookTeamMaps.size(); i++) {
                Map map = JSON.parseObject(JSON.toJSONString(bookTeamMaps.get(i)),Map.class);
                map.put("role_name",roleMapper.selectRoleName(bookTeamMaps.get(i).getRole_id()));
                mapList.add(map);
            }
            rs = new Result("200",null,mapList);
            rs = new Result("200",null,bookTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

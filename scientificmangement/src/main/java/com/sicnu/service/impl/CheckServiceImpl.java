package com.sicnu.service.impl;

import com.sicnu.mapper.*;
import com.sicnu.pojo.Award;
import com.sicnu.pojo.User;
import com.sicnu.service.CheckService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    @Resource
    UserMapper userMapper;
    @Resource
    AwardMapper awardMapper;
    @Resource
    BookMapper bookMapper;
    @Resource
    PaperMapper paperMapper;
    @Resource
    PatentMapper patentMapper;
    @Resource
    ProjectMapper projectMapper;

    private Result rs;
    @Override
    public Result finalCheck() {
        List<Object> list = new ArrayList<>();
        List<Integer> userIds = userMapper.selectAllUserId();
        for (Integer userId : userIds) {
            Map<String,Object> map= new HashMap<>();

            User user = userMapper.findUserById(userId);
            map.put("department",user.getDepartment_id());
            map.put("name",user.getUser_name());
            Integer awardCount = awardMapper.selectCountAward(userId);
            map.put("awardCount",awardCount);
            Integer bookCount = bookMapper.selectCountBook(userId);
            map.put("bookCount",bookCount);
            Integer paperCount = paperMapper.selectCountPaper(userId);
            map.put("paperCount",paperCount);
            Integer patentCount = patentMapper.selectCountPatent(userId);
            map.put("patentCount",patentCount);
            Integer projectCount = projectMapper.selectCountProject(userId);
            map.put("projectCount",projectCount);
            list.add(map);
        }
        rs = new Result("200",null,list);
        return rs;
    }

//    public static void main(String[] args) {
//        List<Object> list = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            map.put("a",i);
//            list.add(map);
//        }
//        Syste                 hnjmh/nm.out.println(list);
//    }
}

package com.sicnu.service.impl;

import com.sicnu.mapper.BookTeamMapper;
import com.sicnu.mapper.UserMapper;
import com.sicnu.pojo.User;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.service.BookTeamService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookTeamServiceImpl implements BookTeamService {

    @Resource
    BookTeamMapper bookTeamMapper;

    @Resource
    UserMapper userMapper;

    private Result rs ;
    @Override
    public Result addBookTeamUser(Integer book_id, String user_name, String user_role, Double contribution,Integer department_id) {
        try {

            int user_id = userMapper.findByUserName(user_name);
            User user = userMapper.findUserById(user_id);
            if (user==null){
                rs = new Result("401", "该用户尚未注册信息或名字与注册名字不同", null);
            }else if (!user.getDepartment_id().equals(department_id)) {
                rs = new Result("400", "成员信息有误，无法添加", null);
            }else{
                bookTeamMapper.addBookTeamUser(book_id, user_id, user_role, contribution);
                rs = new Result("200","添加成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delBookTeamUser(Integer user_id) {
        try {
            bookTeamMapper.delBookTeamUser(user_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectBookTeam(Integer book_id) {
        try {
            List<BookTeamMap> bookTeamMaps = bookTeamMapper.selectBookTeam(book_id);
            rs = new Result("200",null,bookTeamMaps);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

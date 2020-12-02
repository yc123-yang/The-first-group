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
    public Result addBookTeamUser(Integer book_id, Integer user_id, String user_role, Double contribution,Integer department_id) {
        try {
            bookTeamMapper.addBookTeamUser(book_id, user_id, user_role, contribution);
            rs = new Result("200","添加成功",null);
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

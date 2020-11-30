package com.sicnu.controller;

import com.sicnu.mapper.BookTeamMapper;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.service.impl.BookTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class BookTeamController {
    @Resource
    BookTeamServiceImpl bookTeamService;

    private Result rs ;
    @PostMapping("/team/addBookTeamUser")
    public Result addBookTeamUser(Integer book_id, String user_name, String user_role, Double contribution, Integer department_id) {
        try {
            rs= bookTeamService.addBookTeamUser(book_id, user_name, user_role, contribution, department_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/delBookTeamUser")
    public Result delBookTeamUser(Integer user_id) {
        try {
            rs=bookTeamService.delBookTeamUser(user_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/selectBookTeam")
    public Result selectBookTeam(Integer book_id) {
        try {
            rs= bookTeamService.selectBookTeam(book_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

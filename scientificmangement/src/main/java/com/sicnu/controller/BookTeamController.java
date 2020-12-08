package com.sicnu.controller;


import com.sicnu.pojo.BookTeam;
import com.sicnu.service.impl.BookTeamServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class BookTeamController {
    @Resource
    BookTeamServiceImpl bookTeamService;

    private Result rs ;
    @PostMapping("/team/addBookTeamUser")
    public Result addBookTeamUser(Integer book_id, Integer user_id, Integer contribution) {
        try {
            rs= bookTeamService.addBookTeamUser(book_id, user_id, contribution);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/team/delBookTeamUser")
    public Result delBookTeamUser(Integer book_id,Integer user_id) {
        try {
            rs=bookTeamService.delBookTeamUser(book_id,user_id);
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
    @PostMapping("/team/updateBookTeamUser")
    public Result updateBookTeamUser(BookTeam bookTeam) {
        try {
            rs=bookTeamService.updateBookTeamUser(bookTeam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

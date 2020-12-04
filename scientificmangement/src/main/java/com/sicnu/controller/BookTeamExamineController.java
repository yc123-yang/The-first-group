package com.sicnu.controller;

import com.sicnu.mapper.BookTeamExamineMapper;
import com.sicnu.pojo.teamMap.BookTeamMap;
import com.sicnu.service.impl.BookTeamExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RestController
public class BookTeamExamineController {
    @Resource
    BookTeamExamineServiceImpl bookTeamExamineService;
    private Result rs;

    @PostMapping("/teamExamine/selectBookTeamExamineUser")
    public Result selectBookTeamExamineUser(Integer be_id) {
        try {
            rs= bookTeamExamineService.selectBookTeamExamineUser(be_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

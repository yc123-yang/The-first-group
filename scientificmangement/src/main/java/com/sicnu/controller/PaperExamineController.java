package com.sicnu.controller;


import com.sicnu.pojo.PaperExamine;
import com.sicnu.service.impl.PaperExamineServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class PaperExamineController {

    @Resource
    PaperExamineServiceImpl paperExamineService;

    private Result rs;



    @GetMapping("/paperExamine/addPaperExamine")
    public Result addPaperExamine(PaperExamine paperExamine,Integer[] user_id,Double[] contribution,Integer[] periodicalIds) {
        try {
            rs=paperExamineService.addPaperExamine(paperExamine, user_id, contribution,periodicalIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paperExamine/selectPaperExamineByCondition")
    public Result selectPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        try {
            rs = paperExamineService.selectPaperExamineByCondition(paperExamine, publish_time_start, publish_time_end, pageNum, pageSize, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs ;
    }

    @PostMapping("/paperExamine/delPaperExamineById")
    public Result delPaperExamineById(Integer pe_id) {
        rs = paperExamineService.delPaperExamineById(pe_id);
        rs =new Result("200","删除成功",null);
        return rs;
    }

    @PostMapping("/paperExamine/selectAllPaperExamineByCondition")
    public Result selectAllPaperExamineByCondition(PaperExamine paperExamine, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize, String apply_time_start, String apply_time_end) {
        List<Object> list = null;
        try {
            rs = paperExamineService.selectAllPaperExamineByCondition(paperExamine, publish_time_start, publish_time_end, pageNum, pageSize, apply_time_start, apply_time_end);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paperExamine/findPaperExamineById")
    public Result findPaperExamineById(Integer pe_id) {
        try {
            rs = paperExamineService.findPaperExamineById(pe_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

package com.sicnu.controller;

import com.sicnu.pojo.Paper;
import com.sicnu.service.impl.PaperServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;

@Controller
@CrossOrigin
@ResponseBody
public class PaperController {
    @Resource
    PaperServiceImpl paperService;

    private Result rs;

    @PostMapping("/paper/addPaper")
    public Result addPaper(Paper paper,String checkMessage,String message) {
        try {
            rs = paperService.addPaper(paper, checkMessage, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paper/selectPaperByCondition")
    public Result selectPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        try {
            rs = paperService.selectPaperByCondition(paper, publish_time_start, publish_time_end, pageNum, pageSize);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paper/updatePaper")
    public Result updatePaper(Paper paper) {
        try {
            rs = paperService.updatePaper(paper);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paper/delPaper")
    public Result delPaperById(Integer paper_id) {
        try {
            rs = paperService.delPaperById(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paper/selectAllPaperByCondition")
    public Result selectAllPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        try {
            rs = paperService.selectAllPaperByCondition(paper, publish_time_start, publish_time_end, pageNum, pageSize);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/paper/findPaperById")

    public Result findPaperById(Integer paper_id) {
        try {
            rs = paperService.findPaperById(paper_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

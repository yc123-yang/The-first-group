package com.sicnu.service.impl;

import com.sicnu.mapper.PeriodicalPaperMapper;
import com.sicnu.pojo.PeriodicalPaper;
import com.sicnu.service.PeriodicalPaperService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PeriodicalPaperServiceImpl implements PeriodicalPaperService {

    @Resource
    PeriodicalPaperMapper periodicalPaperMapper;
    private Result rs;

    @Override
    public Result addPeriodicalPaper(Integer paper_id, Integer periodical_id) {
        try {
            periodicalPaperMapper.addPeriodicalPaper(paper_id, periodical_id);
            rs = new Result("200","收录期刊添加成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findPeriodicalByPaperId(Integer paper_id) {
        try {
            List<Integer> periodicalIds = periodicalPaperMapper.findPeriodicalByPaperId(paper_id);
            rs = new Result("200",null,periodicalIds);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPeriodicalByPaperId(Integer paper_id) {
        try {
            periodicalPaperMapper.delPeriodicalByPaperId(paper_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delPeriodicalByPeriodicalId(Integer periodical_id) {
        try {
            periodicalPaperMapper.delPeriodicalByPeriodicalId(periodical_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updatePeriodicalPaper(Integer paper_id,Integer[] periodical_ids) {
        try {
            periodicalPaperMapper.delPeriodicalByPaperId(paper_id);
            for (int i = 0; i < periodical_ids.length; i++) {
                PeriodicalPaper periodicalPaper = new PeriodicalPaper();
                periodicalPaperMapper.addPeriodicalPaper(paper_id, periodical_ids[i]);
            }
            rs =new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

}

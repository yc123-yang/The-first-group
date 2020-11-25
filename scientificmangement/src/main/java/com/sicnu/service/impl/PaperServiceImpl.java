package com.sicnu.service.impl;

import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.mapper.PaperMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.pojo.Paper;
import com.sicnu.pojo.Patent;
import com.sicnu.service.PaperService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;
    /**
     * 添加论文
     *
     * @param paper
     * @return
     */
    @Override
    public Result addPaper(Paper paper) {
        Paper paper1 = paperMapper.selectPaperByNumber(paper.getInclude_number());
        if (paper1 != null) {
            rs = new Result("400", "该专利已经存在", null);
        } else {
            paperMapper.addPaper(paper);
            rs = new Result("200", "注册成功", null);
        }
        return rs;
    }

    /**
     * 根据条件查找论文
     *
     * @param paper
     * @param publish_time_start
     * @param publish_time_end
     * @param pageNum
     * @param pageSize
     * @return
     * @throws ParseException
     */
    @Override
    public Result selectPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        Map<String, Object> map = new HashMap<>();


        //获取登陆用户的缓存信息
        List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
        //获取登录用户的id
        Integer uid = cacheUsers.get(0).getUser_id();

        map.put("paper_name", paper.getPaper_name());
        map.put("pt_id", paper.getPt_id());
        map.put("leader_id", uid);
        map.put("periodical_id", paper.getPeriodical_id());
        map.put("publish_time", paper.getPublish_time());
        map.put("include_number", paper.getInclude_number());
        map.put("sc_id", paper.getSc_id());
        map.put("subject_id", paper.getSubject_id());
        map.put("aod_id", paper.getAod_id());
        map.put("sd_id", paper.getSd_id());
        map.put("remark", paper.getRemark());
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!publish_time_start.equals("")) {
            map.put("publish_time_start", sdf.parse(publish_time_start));
        }
        if (!publish_time_end.equals("")) {
            map.put("publish_time_end", sdf.parse(publish_time_end));
        }

        System.out.println("map:"+ map);
        List<Paper> papers = paperMapper.selectPaperByCondition(map);

        Integer total = paperMapper.selectTotalPaper(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("total", total);
        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(papers);
        return rs = new Result("200", null, list);
    }

    /**
     * 更新论文呢信息
     *
     * @param paper
     * @return
     */
    @Override
    public Result updatePaper(Paper paper) {
        paperMapper.updatePaper(paper);
        rs = new Result("200", "更新成功", null);
        return rs;
    }

    /**
     * 删除论文
     *
     * @param paper_id
     * @return
     */
    @Override
    public Result delPaperById(Integer paper_id) {
        paperMapper.delPaperById(paper_id);
        rs = new Result("200", "删除成功", null);
        return rs;
    }
    @Override
    public Result selectAllPaperByCondition(Paper paper, String publish_time_start, String publish_time_end, Integer pageNum, Integer pageSize) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("paper_name", paper.getPaper_name());
        map.put("pt_id", paper.getPt_id());
        map.put("periodical_id", paper.getPeriodical_id());
        map.put("publish_time", paper.getPublish_time());
        map.put("include_number", paper.getInclude_number());
        map.put("sc_id", paper.getSc_id());
        map.put("subject_id", paper.getSubject_id());
        map.put("aod_id", paper.getAod_id());
        map.put("sd_id", paper.getSd_id());
        map.put("remark", paper.getRemark());
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!publish_time_start.equals("")) {
            map.put("publish_time_start", sdf.parse(publish_time_start));
        }
        if (!publish_time_end.equals("")) {
            map.put("publish_time_end", sdf.parse(publish_time_end));
        }

        Integer total = paperMapper.selectTotalPaper(map);
        List<Paper> papers = paperMapper.selectPaperByCondition(map);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("total", total);
        List<Object> list = new ArrayList<>();
        list.add(map1);
        list.add(papers);
        return rs = new Result("200", null, list);
    }

}

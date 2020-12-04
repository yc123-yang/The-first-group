package com.sicnu.service.impl;

import com.sicnu.mapper.BookExamineMapper;
import com.sicnu.mapper.BookTeamExamineMapper;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.pojo.BookExamine;
import com.sicnu.pojo.BookTeam;
import com.sicnu.pojo.CacheUser;
import com.sicnu.service.BookExamineService;
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
public class BookExamineServiceImpl implements BookExamineService {

    @Resource
    BookExamineMapper bookExamineMapper;

    private Result rs;

    @Resource
    BookTeamExamineMapper bookTeamExamineMapper;
    @Resource
    CacheUserMapper cacheUserMapper;

    @Override
    public Result addBookExamine(BookExamine bookExamine,Integer[] user_id,Double[] contribution) {
        try {
            bookExamineMapper.addBookExamine(bookExamine);
            Integer book_id = bookExamineMapper.selectBookExamineId(bookExamine.getLeader_id(),bookExamine.getBook_name());
            for (int i = 0; i < user_id.length; i++) {
                bookTeamExamineMapper.addBookTeamExamineUser(book_id,user_id[i],contribution[i]);
            }
            rs = new Result("200","您的著作成果已经上传审核，请您耐心等待审核结果",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;

        try {
            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();

            Map<String, Object> map = new HashMap<>();
            map.put("book_name", bookExamine.getBook_name());
            map.put("leader_id", uid);
            map.put("press", bookExamine.getPress());
            map.put("pl_id", bookExamine.getPl_id());
            map.put("bt_id", bookExamine.getBt_id());
            map.put("pp_id", bookExamine.getPp_id());
            map.put("isbn", bookExamine.getIsbn());
            map.put("word_number", bookExamine.getWord_number());
            map.put("trans", bookExamine.getTrans());
            map.put("language_id", bookExamine.getLanguage_id());
            map.put("sc_id", bookExamine.getSc_id());
            map.put("subject_id", bookExamine.getSubject_id());
            map.put("aod_id", bookExamine.getAod_id());
            map.put("sd_id", bookExamine.getSd_id());
            map.put("rt_id", bookExamine.getRt_id());
            map.put("examine_status",bookExamine.getExamine_status());
            map.put("reviewer_id",bookExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            List<BookExamine> bookExamines = bookExamineMapper.selectBookExamineByCondition(map);
            Integer total = bookExamineMapper.selectTotalBookExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(bookExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result delBookExamineById(Integer be_id) {
        try {
            bookExamineMapper.delBookExamineById(be_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findBookExamineById(Integer be_id) {
        try {
            BookExamine bookExamine =bookExamineMapper.findBookExamineById(be_id);
            rs = new Result("200",null,bookExamine);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public Result selectAllBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum, String apply_time_start, String apply_time_end) {
        List<Object> list = null;

        try {

            Map<String, Object> map = new HashMap<>();
            map.put("book_name", bookExamine.getBook_name());
            map.put("press", bookExamine.getPress());
            map.put("pl_id", bookExamine.getPl_id());
            map.put("bt_id", bookExamine.getBt_id());
            map.put("pp_id", bookExamine.getPp_id());
            map.put("isbn", bookExamine.getIsbn());
            map.put("word_number", bookExamine.getWord_number());
            map.put("translate", bookExamine.getTrans());
            map.put("language_id", bookExamine.getLanguage_id());
            map.put("sc_id", bookExamine.getSc_id());
            map.put("subject_id", bookExamine.getSubject_id());
            map.put("aod_id", bookExamine.getAod_id());
            map.put("sd_id", bookExamine.getSd_id());
            map.put("rt_id", bookExamine.getRt_id());
            map.put("examine_status",bookExamine.getExamine_status());
            map.put("reviewer_id",bookExamine.getReviewer_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            if (!apply_time_start.equals("")) {
                map.put("apply_time_start", sdf.parse(apply_time_start));
            }
            if (!apply_time_end.equals("")) {
                map.put("apply_time_end", sdf.parse(apply_time_end));
            }
            List<BookExamine> bookExamines = bookExamineMapper.selectBookExamineByCondition(map);
            Integer total = bookExamineMapper.selectTotalBookExamine(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(bookExamines);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }
}

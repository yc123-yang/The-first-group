package com.sicnu.service.impl;

import com.sicnu.mapper.BookMapper;
import com.sicnu.mapper.CacheUserMapper;
import com.sicnu.pojo.CacheUser;
import com.sicnu.service.BookService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;
import com.sicnu.pojo.Book;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService{


    @Resource
    BookMapper bookMapper;

    private Result rs;

    @Resource
    CacheUserMapper cacheUserMapper;
    @Override
    public Result addBook(Book book) {
        try {
            Book book1 = bookMapper.selectBookByNumber(book.getPublish_time());
            if (book1 != null) {

            } else {
                bookMapper.addBook(book);
                rs = new Result("200", "插入成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


    @Override
    public Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;

        try {
            //获取登陆用户的缓存信息
            List<CacheUser> cacheUsers = cacheUserMapper.findAllCacheUser();
            //获取登录用户的id
            Integer uid = cacheUsers.get(0).getUser_id();

            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("leader_id", uid);
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("translate", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            List<Book> books = bookMapper.selectBookByCondition(map);
            Integer total = bookMapper.selectTotalBook(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(books);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }

    @Override
    public Result delBook(Integer book_id) {
        try {
            bookMapper.delBook(book_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateBook(Book book) {
        try {
            bookMapper.updateBook(book);
            rs = new Result("200","更新成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        List<Object> list = null;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("book_name", book.getBook_name());
            map.put("press", book.getPress());
            map.put("pl_id", book.getPl_id());
            map.put("bt_id", book.getBt_id());
            map.put("pp_id", book.getPp_id());
            map.put("isbn", book.getIsbn());
            map.put("word_number", book.getWord_number());
            map.put("translate", book.getTrans());
            map.put("language_id", book.getLanguage_id());
            map.put("sc_id", book.getSc_id());
            map.put("subject_id", book.getSubject_id());
            map.put("aod_id", book.getAod_id());
            map.put("sd_id", book.getSd_id());
            map.put("rt_id", book.getRt_id());
            map.put("pageSize", pageSize);
            map.put("pageNum", pageNum);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!publish_time_start.equals("")) {
                map.put("publish_time_start", sdf.parse(publish_time_start));
            }
            if (!publish_time_end.equals("")) {
                map.put("publish_time_end", sdf.parse(publish_time_end));
            }
            List<Book> books = bookMapper.selectBookByCondition(map);
            Integer total = bookMapper.selectTotalBook(map);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("total", total);
            list = new ArrayList<>();
            list.add(map1);
            list.add(books);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs = new Result("200", null, list);
    }
}

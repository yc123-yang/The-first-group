package com.sicnu.service.impl;

import com.sicnu.mapper.BookMapper;
import com.sicnu.pojo.Book;
import com.sicnu.service.BookService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;

    private Result rs;

    @Override
    public Result addBook(Book book) {
        Book book1 = bookMapper.selectBookByNumber(book.getPublishTime());
        if (book1 != null) {

        } else {
            bookMapper.addBook(book);
            rs = new Result("200", "插入成功", null);
        }
        return rs;
    }


    @Override
    public Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        map.put("book_name", book.getBookName());
        map.put("leader_id", book.getLeaderId());
        map.put("press", book.getPress());
        map.put("pl_id", book.getPlId());
        map.put("bt_id", book.getBtId());
        map.put("pp_id", book.getPpId());
        map.put("isbn", book.getIsbn());
        map.put("word_number", book.getWordNumber());
        map.put("translate", book.getTrans());
        map.put("language_id", book.getLanguageId());
        map.put("sc_id", book.getScId());
        map.put("subject_id", book.getSubjectId());
        map.put("aod_id", book.getAodId());
        map.put("sd_id", book.getSdId());
        map.put("rt_id", book.getRtId());
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (publish_time_start.equals("")) {
            map.put("publish_time_start", sdf.parse(publish_time_start));
        }
        if (publish_time_end.equals("")) {
            map.put("publish_time_end", sdf.parse(publish_time_end));
        }
        List<Book> books = bookMapper.selectBookByCondition(map);
        rs = new Result("200", null, books);
        return rs;
    }

    @Override
    public Result delBook(Integer book_id) {
        bookMapper.updateBook(book_id);
        return null;
    }

    @Override
    public Result updateBook(Integer book_id) {
        bookMapper.updateBook(book_id);
        return null;
    }
}

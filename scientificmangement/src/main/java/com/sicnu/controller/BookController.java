package com.sicnu.controller;

import com.sicnu.pojo.Book;
import com.sicnu.service.impl.BookServiceImpl;
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
public class BookController {
    @Resource
    BookServiceImpl bookService;

    private Result rs;

    @PostMapping("/book/addBook")
    public Result addBook(Book book,String checkMessage,String message) {
        try {
            rs = bookService.addBook(book, checkMessage, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/book/selectBookByCondition")
    public Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        try {
            rs = bookService.selectBookByCondition(book, publish_time_start, publish_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/book/delBook")
    public Result delBook(Integer book_id) {
        try {
            rs = bookService.delBook(book_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/book/updateBook")

    public Result updateBook(Book book) {
        try {
            rs = bookService.updateBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/book/selectAllBookByCondition")
    public Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException {
        try {
            rs = bookService.selectAllBookByCondition(book, publish_time_start, publish_time_end, pageSize, pageNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

}

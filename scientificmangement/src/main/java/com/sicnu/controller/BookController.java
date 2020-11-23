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
    public Result addBook(Book book) {
        rs = bookService.addBook(book);
        return rs;
    }

    @PostMapping("/book/selectBookByCondition")
    public Result selectBookByCondition(Book book,String publish_time_start,String publish_time_end,Integer pageSize,Integer pageNum) throws ParseException {
        rs = bookService.selectBookByCondition(book, publish_time_start, publish_time_end, pageSize, pageNum);
        return rs;
    }

    @PostMapping("/book/delBook")
    public Result delBook(Integer book_id) {
        rs = bookService.delBook(book_id);
        return  rs;
    }

    @PostMapping("/book/updateBook")

    public Result updateBook(Integer book_id) {
        rs = bookService.updateBook(book_id);
        return rs;
    }
}

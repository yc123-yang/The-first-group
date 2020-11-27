package com.sicnu.service;

import com.sicnu.pojo.Book;
import com.sicnu.util.Result;


import java.text.ParseException;

public interface BookService {
    Result addBook(Book book);

    Result selectBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException;

    Result delBook(Integer book_id);

    Result updateBook(Book book);
    Result selectAllBookByCondition(Book book, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum) throws ParseException;

}

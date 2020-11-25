package com.sicnu.mapper;

import com.sicnu.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookMapper {
    void addBook(Book book);

    Book selectBookByNumber(Date publish_time);

    List<Book> selectBookByCondition(Map<String, Object> map);

    void delBook(Integer book_id);

    void updateBook(Integer book_id);
    int selectTotalBook(Map<String, Object> map);
}

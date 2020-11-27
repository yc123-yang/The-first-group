package com.sicnu.mapper;

import com.sicnu.pojo.BookType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookTypeMapper {
    void addBookType(String bt_name);
    void delBookType(Integer bt_id);
    List<BookType> findAllBookType();
    BookType selectBookTypeByName(String bt_name);
    void updateBookType(BookType bookType);
}

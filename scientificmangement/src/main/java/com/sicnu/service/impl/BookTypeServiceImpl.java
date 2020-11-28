package com.sicnu.service.impl;

import com.sicnu.mapper.BookTypeMapper;
import com.sicnu.pojo.BookType;
import com.sicnu.service.BookTypeService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BookTypeServiceImpl implements BookTypeService {

    @Resource
    BookTypeMapper bookTypeMapper;
    private Result rs = null;
    @Override
    public Result addBookType(String bt_name) {

        try {
            BookType bookType = bookTypeMapper.selectBookTypeByName(bt_name);
            if (bookType!=null){
                rs = new Result("400","该字典已存在",null);
            }else{
                bookTypeMapper.addBookType(bt_name);
                rs = new Result("200","该字典插入成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }

    @Override
    public Result delBookType(Integer bt_id) {

        try {
            bookTypeMapper.delBookType(bt_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllBookType() {
        try {
            List<BookType> bookTypes = bookTypeMapper.findAllBookType();
            rs = new Result("200",null,bookTypes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateBookType(BookType bookType) {
        try {
           bookTypeMapper.updateBookType(bookType);
           rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}

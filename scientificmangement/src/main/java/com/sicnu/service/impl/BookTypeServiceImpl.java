package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.BookTypeMapper;
import com.sicnu.pojo.BookType;
import com.sicnu.service.BookTypeService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class BookTypeServiceImpl implements BookTypeService {

    @Resource
    BookTypeMapper bookTypeMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;

    private Result rs = null;
    @Override
    public Result addBookType(String bt_name) {

        try {
            BookType bookType = bookTypeMapper.selectBookTypeByName(bt_name);
            if (bookType!=null){
                rs = new Result("400","该字典已存在",null);
            }else{
                bookTypeMapper.addBookType(bt_name);
                redisUtil.del("bookTypes");
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
            redisUtil.del("bookTypes");
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllBookType() {
        try {
            List bookTypes = new ArrayList();

            if (redisUtil.hasKey("bookTypes")) {
                log.warn("从redis中获取数据.");
                bookTypes = redisList.get("bookTypes", 0, -1);
            } else {
                bookTypes = bookTypeMapper.findAllBookType();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("bookTypes", bookTypes);
                log.info("成功存入redis.");
            }
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
            redisUtil.del("bookTypes");
           rs = new Result("200","更改成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }


}

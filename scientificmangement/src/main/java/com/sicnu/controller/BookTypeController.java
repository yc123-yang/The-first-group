package com.sicnu.controller;

import com.sicnu.pojo.BookType;
import com.sicnu.service.BookTypeService;
import com.sicnu.service.impl.BookTypeServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class BookTypeController {

    @Resource
    BookTypeServiceImpl bookTypeService;
    private Result rs = null;
    @PostMapping("/bookType/addBookType")
    public Result addBookType(String bt_name) {

        try {
            rs =bookTypeService.addBookType(bt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
    @PostMapping("/bookType/delBookType")

    public Result delBookType(Integer bt_id) {

        try {
            rs = bookTypeService.delBookType(bt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/bookType/findAllBookType")

    public Result findAllBookType() {
        try {
            rs = bookTypeService.findAllBookType();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

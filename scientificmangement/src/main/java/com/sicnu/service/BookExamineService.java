package com.sicnu.service;


import com.sicnu.pojo.Book;
import com.sicnu.pojo.BookExamine;
import com.sicnu.util.Result;

import java.util.List;
import java.util.Map;

public interface BookExamineService {
    Result addBookExamine(BookExamine bookExamine);
    Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delBookExamineById(Integer be_id);
}

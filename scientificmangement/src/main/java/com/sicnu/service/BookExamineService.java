package com.sicnu.service;


import com.sicnu.pojo.BookExamine;
import com.sicnu.util.Result;


public interface BookExamineService {
    Result addBookExamine(BookExamine bookExamine,Integer[] user_id,Double[] contribution);
    Result selectBookExamineByCondition(BookExamine bookExamine, String publish_time_start, String publish_time_end, Integer pageSize, Integer pageNum,String apply_time_start,String apply_time_end);
    Result delBookExamineById(Integer be_id);
    Result findBookExamineById(Integer be_id);

}

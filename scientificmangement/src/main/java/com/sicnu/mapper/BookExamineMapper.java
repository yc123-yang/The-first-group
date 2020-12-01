package com.sicnu.mapper;

import com.sicnu.pojo.BookExamine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookExamineMapper {
    void addBookExamine(BookExamine bookExamine);
    List<BookExamine> selectBookExamineByCondition(Map<String,Object> map);
    void delBookExamineById(Integer be_id);
    Integer selectTotalBookExamine(Map<String,Object> map);
    void delBookExamine(Integer leader_id,String book_name);
}

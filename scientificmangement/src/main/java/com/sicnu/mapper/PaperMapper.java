package com.sicnu.mapper;

import com.sicnu.pojo.Paper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PaperMapper {
    void addPaper(Paper paper);

    Paper selectPaperByNumber(String include_number);

    List<Paper> selectPaperByCondition(Map<String, Object> map);

    void updatePaper(Paper paper);

    void delPaperById(Integer paper_id);
}

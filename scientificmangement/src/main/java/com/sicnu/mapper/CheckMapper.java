package com.sicnu.mapper;

import com.sicnu.pojo.Check;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CheckMapper {
    void addCheck(Check check);
    List<Check> selectCheckByCondition();
    void delCheck();
}

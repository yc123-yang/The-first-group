package com.sicnu.dao;

import com.sicnu.pojo.Check_Email;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface Check_EmailDao {
    void addCheckCode(String email, Integer check_code, Date overtime);
    List<Check_Email> findAllCode();
    void delCode();
    Check_Email findByCode(Integer check_code);
}

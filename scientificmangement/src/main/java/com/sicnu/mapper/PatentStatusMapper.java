package com.sicnu .mapper;

import com.sicnu.pojo.PatentStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PatentStatusMapper {
    void addPatentStatus(String ps_name);
    void delPatentStatus(Integer ps_id);
    List<PatentStatus> findAllPatentStatus();
    PatentStatus selectPatentStatusByName(String ps_name);
    void updatePatentRange(PatentStatus patentStatus);
}

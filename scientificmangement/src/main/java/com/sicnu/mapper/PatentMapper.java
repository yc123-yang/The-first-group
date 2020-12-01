package com.sicnu.mapper;

import com.sicnu.pojo.Patent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Mapper
@Repository
public interface PatentMapper {
    void addPatent(Patent patent);

    Patent selectPatentByNumber(String application_number, String public_number, String authorization_number);


    List<Patent> selectPatentByCondition(Map<String, Object> map);

    void updatePatent(Patent patent);

    Integer selectTotalPatent(Map<String, Object> map);

    Integer selectCountPatent(Map<String,Object> map);

    List<Patent> findPatentByLeaderId(Integer leader_id);

    Integer selectPatentId(Integer leader_id,String petent_name);

    void delPatentById(Integer patent_id);
}

package com.sicnu.mapper;

import com.sicnu.pojo.Patent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Mapper
@Repository
public interface PatentMapper {
    void addPatent(Patent patent);
}

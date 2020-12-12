package com.sicnu.mapper;

import com.sicnu.pojo.Outlay;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OutlayMapper {
    void addOutlay(Outlay outlay);
    List<Outlay> selectAllOutlay();
    void delOutlayById(Integer outlay_id);
    void updateOutlay(Outlay outlay);
    Integer selectOutlayScore(Integer outlay);
}

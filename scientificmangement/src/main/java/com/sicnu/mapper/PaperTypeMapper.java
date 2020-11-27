package com.sicnu.mapper;

import com.sicnu.pojo.PaperType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperTypeMapper {

    void addPaperType(String pt_name);
    void delPaperType(Integer pt_id);
    List<PaperType> findAllPaperType();
    PaperType selectPaperTypeByName(String pt_name);
    void updatePaperType(PaperType paperType);
}

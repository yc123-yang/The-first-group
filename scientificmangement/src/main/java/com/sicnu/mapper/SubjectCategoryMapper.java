package com.sicnu.mapper;

import com.sicnu.pojo.SubjectCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectCategoryMapper {
    void addSubjectCategory(String sc_name);
    void delSubjectCategory(Integer sc_id);
    List<SubjectCategory> findAllSubjectCategory();
    SubjectCategory selectSubjectCategoryByName(String sc_name);
}

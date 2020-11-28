package com.sicnu.mapper;

import com.sicnu.pojo.Subject;
import com.sicnu.pojo.SubjectCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectCategoryMapper {
    void addSubjectCategory(String sc_id, String sc_name);

    void delSubjectCategory(String sc_id);

    List<SubjectCategory> findAllSubjectCategory();

    SubjectCategory selectSubjectCategoryByName(String sc_name);

    List<SubjectCategory> selectSubjectCategory(String sc_id);
    void updateSubjectCategory(SubjectCategory subjectCategory);
}

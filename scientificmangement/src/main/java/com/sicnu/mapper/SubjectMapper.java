package com.sicnu.mapper;

import com.sicnu.pojo.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectMapper {
    void addSubject(String subject_name);
    void delSubject(Integer subject_id);
    List<Subject> findAllSubject();
    Subject selectSubjectByName(String subject_name);
}

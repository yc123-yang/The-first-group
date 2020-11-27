package com.sicnu.mapper;

import com.sicnu.pojo.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SubjectMapper {
    void addSubject(String subject_id, String subject_name, String sc_id);

    void delSubject(String subject_id);

    List<Subject> findAllSubject();

    Subject selectSubjectByName(String subject_name);
    void updateSubject(Subject subject);
}

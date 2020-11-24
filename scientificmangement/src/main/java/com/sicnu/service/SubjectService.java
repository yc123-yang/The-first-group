package com.sicnu.service;

import com.sicnu.pojo.Subject;
import com.sicnu.util.Result;

import java.util.List;

public interface SubjectService {
    Result addSubject(String subject_id, String subject_name, String sc_id);

    Result delSubject(String subject_id);

    Result findAllSubject();

}

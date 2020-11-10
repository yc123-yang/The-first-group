package com.sicnu.service;

import com.sicnu.util.Result;

public interface SubjectService {
    Result addSubject(String subject_name);
    Result delSubject(Integer subject_id);
    Result findAllSubject();
}

package com.sicnu.service;

import com.sicnu.pojo.Subject;
import com.sicnu.util.Result;

import java.util.List;

public interface SubjectService {
    Result addSubject(String subject_name);
    Result delSubject(Integer subject_id);
    Result findAllSubject();
}

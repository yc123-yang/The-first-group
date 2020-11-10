package com.sicnu.service;

import com.sicnu.pojo.SubjectCategory;
import com.sicnu.util.Result;

import java.util.List;

public interface SubjectCategoryService {
    Result addSubjectCategory(String sc_name);
    Result delSubjectCategory(Integer sc_id);
    Result findAllSubjectCategory();
}

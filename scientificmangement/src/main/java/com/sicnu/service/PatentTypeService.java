package com.sicnu.service;

import com.sicnu.pojo.PatentType;
import com.sicnu.util.Result;


public interface PatentTypeService {
    Result addPatentType(String pt_name);
    Result delPatentType(Integer pt_id);
    Result findAllPatentType();
    Result updatePatentType(PatentType patentType);
}

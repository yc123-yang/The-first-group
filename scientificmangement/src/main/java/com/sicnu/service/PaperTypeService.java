package com.sicnu.service;

import com.sicnu.pojo.PaperType;
import com.sicnu.util.Result;

import java.util.List;

public interface PaperTypeService {
    Result addPaperType(String pt_name);
    Result delPaperType(Integer pt_id);
    Result findAllPaperType();
}

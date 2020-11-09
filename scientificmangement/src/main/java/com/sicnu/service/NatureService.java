package com.sicnu.service;

import com.sicnu.pojo.Nature;
import com.sicnu.util.Result;

import java.util.List;

public interface NatureService {
    Result addNature(String nature_name);
    Result delNature(Integer nature_id);
    Result findAllNature();
}

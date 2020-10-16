package com.sicnu.service;

import com.sicnu.pojo.Project;
import com.sicnu.util.Result;

public interface ProjectService {
    Result selectProjectById(String project_name);
}

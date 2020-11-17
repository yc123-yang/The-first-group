package com.sicnu.service.impl;

import com.sicnu.mapper.PatentMapper;
import com.sicnu.pojo.Patent;
import com.sicnu.service.PatentService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PatentServiceImpl implements PatentService {
    @Resource
    PatentMapper patentMapper;

    /**
     * 添加专利
     * @param patent
     * @return
     */
    @Override
    public Result addPatent(Patent patent) {
        patentMapper.addPatent(patent);
        return null;
    }
}

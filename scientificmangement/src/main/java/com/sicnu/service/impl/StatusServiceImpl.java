package com.sicnu.service.impl;

import com.sicnu.mapper.StatusMapper;
import com.sicnu.pojo.Status;
import com.sicnu.service.StatusService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目状态
 */
@Service
public class StatusServiceImpl implements StatusService {
    @Resource
    StatusMapper statusMapper;
    private Result rs;

    @Override
    public Result addStatus(String status_name) {

        Status status = statusMapper.selectStatusByName(status_name);
        if (status == null) {
            statusMapper.addStatus(status_name);
            rs = new Result("0", "添加项目状态字典成功", null);
        } else {
            rs = new Result("1", "该项目状态字典已经存在", null);

        }
        return rs;
    }

    @Override
    public Result delStatus(Integer status_id) {
        statusMapper.delStatus(status_id);
        rs = new Result("0", "删除项目状态成功", null);
        return rs;
    }

    @Override
    public Result findAllStatus() {
        List<Status> statuses = statusMapper.findAllStatus();
        rs = new Result("0", null, statuses);
        return rs;
    }
}

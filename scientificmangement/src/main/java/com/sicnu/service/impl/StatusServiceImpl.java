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

        try {
            Status status = statusMapper.selectStatusByName(status_name);
            if (status == null) {
                statusMapper.addStatus(status_name);
                rs = new Result("200", "添加项目状态字典成功", null);
            } else {
                rs = new Result("401", "该项目状态字典已经存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delStatus(Integer status_id) {
        try {
            statusMapper.delStatus(status_id);
            rs = new Result("200", "删除项目状态成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllStatus() {
        try {
            List<Status> statuses = statusMapper.findAllStatus();
            rs = new Result("400", null, statuses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateStatus(Status status) {
        try {
            statusMapper.updateStatus(status);
            rs = new Result("200", "更改项目状态字典成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

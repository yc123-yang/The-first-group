package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.StatusMapper;
import com.sicnu.pojo.Status;
import com.sicnu.service.StatusService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目状态
 */
@Service
@Slf4j
public class StatusServiceImpl implements StatusService {
    @Resource
    StatusMapper statusMapper;

    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;

    @Override
    public Result addStatus(String status_name) {

        try {
            Status status = statusMapper.selectStatusByName(status_name);
            if (status == null) {
                statusMapper.addStatus(status_name);
                redisUtil.del("statuses");
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
            redisUtil.del("statuses");
            rs = new Result("200", "删除项目状态成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllStatus() {
        try {
            List statuses = new ArrayList<>();
            if (redisUtil.hasKey("statuses")) {
                log.warn("从redis中获取数据.");
                statuses = redisList.get("statuses", 0, -1);
            } else {
                statuses = statusMapper.findAllStatus();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("statuses", statuses);
                log.info("成功存入redis.");
            }
            rs = new Result("200", null, statuses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateStatus(Status status) {
        try {
            statusMapper.updateStatus(status);
            redisUtil.del("statuses");
            rs = new Result("200", "更改项目状态字典成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

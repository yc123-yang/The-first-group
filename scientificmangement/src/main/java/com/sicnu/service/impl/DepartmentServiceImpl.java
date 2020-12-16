package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.DepartmentMapper;
import com.sicnu.pojo.Department;
import com.sicnu.service.DepartmentService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * l用户来源部门
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    DepartmentMapper departmentMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;

    @Override
    public Result addDepartment(String department_name) {

        try {
            Department department = departmentMapper.selectDepartmentByName(department_name);
            if (department == null) {
                departmentMapper.addDepartment(department_name);
                redisUtil.del("departments");
                rs = new Result("200", "部门添加成功", null);
            } else {
                rs = new Result("400", "该部门已存在", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delDepartment(Integer department_id) {
        try {
            departmentMapper.delDepartment(department_id);
            redisUtil.del("departments");
            rs = new Result("200", "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result findAllDepartment() {
        try {
            List departments = new ArrayList();
            if (redisUtil.hasKey("departments")) {
                log.warn("从redis中获取数据.");
                departments = redisList.get("departments", 0, -1);
            } else {
                departments = departmentMapper.findAllDepartment();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("departments", departments);
                log.info("成功存入redis.");
            }

            rs = new Result("200", null, departments);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @Override
    public Result updateDepartment(Department department) {
        try {
            departmentMapper.updateDepartment(department);
            redisUtil.del("departments");
            rs = new Result("200", "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}

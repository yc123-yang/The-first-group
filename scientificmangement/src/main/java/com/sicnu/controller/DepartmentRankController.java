package com.sicnu.controller;

import com.sicnu.mapper.DepartmentRankMapper;
import com.sicnu.pojo.DepartmentRank;
import com.sicnu.service.impl.DepartmentRankServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class DepartmentRankController {

    @Resource
    DepartmentRankServiceImpl departmentRankService;

    private Result rs;

    @PostMapping("/departmentRank/addDepartmentRank")
    @RequiresPermissions("/data")
    public Result addDepartmentRank(String dr_name) {

        try {
            rs = departmentRankService.addDepartmentRank(dr_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/departmentRank/delDepartmentRank")
    @RequiresPermissions("/data")
    public Result delDepartmentRank(Integer dr_id) {
        try {
            rs = departmentRankService.delDepartmentRank(dr_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/departmentRank/findAllDepartmentRank")
    public Result findAllDepartmentRank() {
        try {
            rs = departmentRankService.findAllDepartmentRank();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/departmentRank/updateDepartmentRank")
    @RequiresPermissions("/data")
    public Result updateDepartmentRank(DepartmentRank departmentRank) {
        try {
            if (departmentRank.getDr_name().equals("")){
                rs = new Result("400","更改的字典数据不能为空",null);
            }else{
                departmentRankService.updateDepartmentRank(departmentRank);
                rs = new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
}

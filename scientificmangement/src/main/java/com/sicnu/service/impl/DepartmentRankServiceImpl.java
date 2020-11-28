package com.sicnu.service.impl;

import com.sicnu.mapper.DepartmentMapper;
import com.sicnu.mapper.DepartmentRankMapper;
import com.sicnu.pojo.DepartmentRank;
import com.sicnu.service.DepartmentRankService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentRankServiceImpl implements DepartmentRankService {
    @Resource
    DepartmentRankMapper departmentRankMapper;

    private Result rs;
    @Override
    public Result addDepartmentRank(String dr_name) {

        try {
            DepartmentRank departmentRank = departmentRankMapper.selectDepartmentRankByName(dr_name);
            if (departmentRank==null){
                departmentRankMapper.addDepartmentRank(dr_name);
                rs = new Result("200","字典数据插入成功",null);
            }else{
                rs = new Result("200","字典数据已经存在，请勿重复插入",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delDepartmentRank(Integer dr_id) {
        try {
            departmentRankMapper.delDepartmentRank(dr_id);
            rs = new Result("200","删除成功",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllDepartmentRank() {
        try {
            List<DepartmentRank> departmentRanks = departmentRankMapper.findAllDepartmentRank();
            rs = new Result("200",null,departmentRanks);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }



    @Override
    public Result updateDepartmentRank(DepartmentRank departmentRank) {
        try {
            if (departmentRank.getDr_name().equals("")){
                rs = new Result("400","更改的字典数据不能为空",null);
            }else{
                departmentRankMapper.updateDepartmentRank(departmentRank);
                rs = new Result("200","更改成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;

    }
}

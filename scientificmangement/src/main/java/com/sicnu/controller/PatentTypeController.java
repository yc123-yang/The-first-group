package com.sicnu.controller;

import com.sicnu.mapper.PatentTypeMapper;
import com.sicnu.pojo.PatentType;
import com.sicnu.service.impl.PatentTypeServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
public class PatentTypeController {
    @Resource
    PatentTypeServiceImpl patentTypeService;

    private Result rs ;

    @PostMapping("/patentType/addPatentType")
    @RequiresPermissions("/data")
    public Result addPatentType(String pt_name) {
        try {
            rs =patentTypeService.addPatentType(pt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentType/delPatentType")
    @RequiresPermissions("/data")
    public Result delPatentType(Integer pt_id) {
        try {
            rs = patentTypeService.delPatentType(pt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentType/findAllPatentType")
    public Result findAllPatentType() {
        try {
            rs = patentTypeService.findAllPatentType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/patentType/updatePatentType")
    @RequiresPermissions("/data")
    public Result updatePatentType(PatentType patentType) {

        try {
            patentTypeService.updatePatentType(patentType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }
}

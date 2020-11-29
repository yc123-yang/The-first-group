package com.sicnu.controller;


import com.sicnu.pojo.Nature;
import com.sicnu.service.impl.NatureServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@CrossOrigin
@RestController
public class NatureController {

    @Resource
    NatureServiceImpl natureService;
    private Result rs;

    @PostMapping("/nature/addNature")
    @RequiresPermissions("/data")
    public Result addNature(String nature_name) {
        try {
            rs = natureService.addNature(nature_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/nature/delNature")
    @RequiresPermissions("/data")
    public Result delNature(Integer nature_id) {
        try {
            rs = natureService.delNature(nature_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/nature/findAllNature")
    public Result findAllNature() {
        try {
            rs = natureService.findAllNature();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
    @PostMapping("/nature/updateNature")
    @RequiresPermissions("/data")
    public Result updateNature(Nature nature) {
        try {
            rs=natureService.updateNature(nature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

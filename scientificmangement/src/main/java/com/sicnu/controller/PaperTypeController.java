package com.sicnu.controller;

import com.sicnu.service.impl.PaperTypeServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class PaperTypeController {
    Result rs = null;
    @Resource
    PaperTypeServiceImpl paperTypeService;

    @PostMapping("/paperType/addPaperType")
    public Result addPaperType(String pt_name) {
        rs = paperTypeService.addPaperType(pt_name);

        return rs;
    }

    @PostMapping("/paperType/delPaperType")

    public Result delPaperType(Integer pt_id) {
        rs=paperTypeService.delPaperType(pt_id);

        return rs;
    }
    @PostMapping("/paperType/findAllPaperType")

    public Result findAllPaperType() {
        rs = paperTypeService.findAllPaperType();
        return rs;
    }
}

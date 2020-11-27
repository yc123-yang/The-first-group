package com.sicnu.controller;

import com.sicnu.pojo.ResearchType;
import com.sicnu.service.ResearchTypeService;
import com.sicnu.service.impl.ResearchTypeServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@Controller
@ResponseBody
public class ResearchTypeController {
    @Resource
    ResearchTypeServiceImpl researchTypeService;
    private Result rs;

    @PostMapping("/researchType/addResearchType")
   public Result addResearchType(String rt_name) {
        try {
            rs = researchTypeService.addResearchType(rt_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/delResearchType")

    public Result delResearchType(Integer rt_id) {

        try {
            rs = researchTypeService.delResearchType(rt_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/findAllResearchType")

    public Result findAllResearchType() {
        try {
            rs = researchTypeService.findAllResearchType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/researchType/updateResearchType")

    public Result updateResearchType(ResearchType researchType) {

        try {
            rs =researchTypeService.updateResearchType(researchType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

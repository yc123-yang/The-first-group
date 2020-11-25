package com.sicnu.controller;

import com.sicnu.service.impl.SubjectCategoryServiceImpl;
import com.sicnu.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@CrossOrigin
@ResponseBody
public class SubjectCategoryController {

    @Resource
    SubjectCategoryServiceImpl categoryService;

    private Result rs;

    @PostMapping("/category/addSubjectCategory")
    public Result addSubjectCategory(String sc_id, String sc_name) {

        try {
            rs = categoryService.addSubjectCategory(sc_id, sc_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/category/delSubjectCategory")
    public Result delSubjectCategory(String sc_id) {

        try {
            rs = categoryService.delSubjectCategory(sc_id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rs;
    }

    @PostMapping("/category/findAllSubjectCategory")
    public Result findAllSubjectCategory() {
        try {
            rs = categoryService.findAllSubjectCategory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/category/selectSubjectCategory")

    public Result selectSubjectCategory(String sc_id) {
        try {
            rs = categoryService.selectSubjectCategory(sc_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

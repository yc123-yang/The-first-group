package com.sicnu.controller;

import com.sicnu.pojo.Language;
import com.sicnu.service.impl.LanguageServiceImpl;
import com.sicnu.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@CrossOrigin
@Controller
@ResponseBody
public class LanguageController {
    @Resource
    LanguageServiceImpl languageService;

    private Result rs;

    @PostMapping("/language/addLanguage")
    @RequiresPermissions("/data")
    public Result addLanguage(String language_name) {
        try {
            rs =languageService.addLanguage(language_name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/language/delLanguage")
    @RequiresPermissions("/data")
    public Result delLanguage(Integer language_id) {
        try {
            rs =languageService.delLanguage(language_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/language/findAllLanguage")
    public Result findAllLanguage() {
        try {
            rs =  languageService.findAllLanguage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @PostMapping("/language/updateLanguage")
    @RequiresPermissions("/data")
    public Result updateLanguage(Language language) {
        try {
            rs=languageService.updateLanguage(language);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

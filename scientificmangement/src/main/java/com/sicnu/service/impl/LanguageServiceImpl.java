package com.sicnu.service.impl;

import com.sicnu.mapper.LanguageMapper;
import com.sicnu.pojo.Language;
import com.sicnu.service.LanguageService;
import com.sicnu.util.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {

    @Resource
    LanguageMapper languageMapper;

    private Result rs;
    @Override
    public Result addLanguage(String language_name) {
        try {
            Language language = languageMapper.selectLanguageByName(language_name);
            if (language.equals("")){
                languageMapper.addLanguage(language_name);
                rs = new Result("200","插入字典数据成功",null);
            }{
                rs = new Result("400","该字典数据已经存在",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result delLanguage(Integer language_id) {
        try {
            languageMapper.delLanguage(language_id);
            rs = new Result("200","删除字典数据成功",null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllLanguage() {
        try {
            List<Language> languages =  languageMapper.findAllLanguage();
            rs = new Result("200",null,languages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result updateLanguage(Language language) {
        try {
            if (language.toString().equals("")){
                rs = new Result("400","更改字典数据不能为空",null);
            }else {
                languageMapper.updateLanguage(language);
                rs = new Result("200","字典数据更新成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

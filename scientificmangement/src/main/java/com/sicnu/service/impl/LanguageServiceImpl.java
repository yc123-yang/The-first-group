package com.sicnu.service.impl;

import com.sicnu.component.RedisUtil;
import com.sicnu.mapper.LanguageMapper;
import com.sicnu.pojo.Language;
import com.sicnu.service.LanguageService;
import com.sicnu.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LanguageServiceImpl implements LanguageService {

    @Resource
    LanguageMapper languageMapper;
    @Resource
    private RedisUtil.redisList redisList;

    @Autowired
    private RedisUtil redisUtil;
    private Result rs;
    @Override
    public Result addLanguage(String language_name) {
        try {
            Language language = languageMapper.selectLanguageByName(language_name);
            if (language==null){
                languageMapper.addLanguage(language_name);
                redisUtil.del("languages");
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
            redisUtil.del("languages");
            rs = new Result("200","删除字典数据成功",null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    public Result findAllLanguage() {
        try {
            List languages =  new ArrayList();

            if (redisUtil.hasKey("languages")) {
                log.warn("从redis中获取数据.");
                languages = redisList.get("languages", 0, -1);
            } else {
                languages = languageMapper.findAllLanguage();
                log.warn("从数据库中获取数据.");
                log.warn("将数据存入redis...");
                redisList.set("languages", languages);
                log.info("成功存入redis.");
            }
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
                redisUtil.del("languages");
                rs = new Result("200","字典数据更新成功",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }
}

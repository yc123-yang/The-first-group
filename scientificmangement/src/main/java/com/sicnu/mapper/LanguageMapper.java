package com.sicnu.mapper;

import com.sicnu.pojo.Language;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LanguageMapper {
    void addLanguage(String language_name);
    void delLanguage(Integer language_id);
    List<Language> findAllLanguage();
    Language selectLanguageByName(String language_name);
    void updateLanguage(Language language);
}

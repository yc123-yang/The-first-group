package com.sicnu.pojo;

/**
 * 翻译语种
 */
public class Language {
    private Integer language_id;
    private String language_name;

    public Integer getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Integer language_id) {
        this.language_id = language_id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    @Override
    public String toString() {
        return "Language{" +
                "language_id=" + language_id +
                ", language_name='" + language_name + '\'' +
                '}';
    }
}

package com.khanh.dictionary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VocabularyType {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("desc")
    @Expose
    public String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public VocabularyType() {
    }

    public VocabularyType(Integer id, String code, String name, String desc) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", code:'" + code +
                ", name:'" + name +
                ", desc:'" + desc +
                '}';
    }
}

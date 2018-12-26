package com.khanh.dictionary.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Vocabulary {
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("type")
    @Expose
    public Integer type;
    @SerializedName("desc")
    @Expose
    public String desc;

    public Vocabulary() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

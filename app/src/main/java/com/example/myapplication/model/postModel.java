package com.example.myapplication.model;

import java.io.Serializable;

public class postModel {
    public class ItemData implements Serializable{}
    String post_id      ;
    String user_mac     ;
    String title        ;
    String note         ;
    String cartetory_id ;
    int like_cnt     ;
    String create_dt    ;
    String update_dt    ;

    public postModel(String post_id, String user_mac, String title, String note, String cartetory_id) {
        this.post_id = post_id;
        this.user_mac = user_mac;
        this.title = title;
        this.note = note;
        this.cartetory_id = cartetory_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_mac() {
        return user_mac;
    }

    public void setUser_mac(String user_mac) {
        this.user_mac = user_mac;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCartetory_id() {
        return cartetory_id;
    }

    public void setCartetory_id(String cartetory_id) {
        this.cartetory_id = cartetory_id;
    }

    public int getLike_cnt() {
        return like_cnt;
    }

    public void setLike_cnt(int like_cnt) {
        this.like_cnt = like_cnt;
    }

    public String getCreate_dt() {
        return create_dt;
    }

    public void setCreate_dt(String create_dt) {
        this.create_dt = create_dt;
    }

    public String getUpdate_dt() {
        return update_dt;
    }

    public void setUpdate_dt(String update_dt) {
        this.update_dt = update_dt;
    }
}

package com.example.myapplication.model;

/**
 * Created by Aws on 28/01/2018.
 */

public class TxtModel {

    private String Title;//제목
    private String Category ;//카테고리
    private String text ; //본문
    private int Thumbnail ;//썸네일
    private int likeCnt; //좋아요 갯수
    private int txtId;   //게시물 넘버

    public TxtModel() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getTxtId() {
        return txtId;
    }

    public void setTxtId(int txtId) {
        this.txtId = txtId;
    }

    public TxtModel(String title, String category, String text, int thumbnail) {
        Title = title;
        Category = category;
        this.text = text;
        Thumbnail = thumbnail;
    }

}

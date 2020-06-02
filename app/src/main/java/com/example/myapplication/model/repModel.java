package com.example.myapplication.model;

public class repModel {
    String tokenId;
    String imageUrl;
    String txt;

    public repModel(String tokenId, String imageUrl, String txt, int likeCnt) {
        this.tokenId = tokenId;
        this.imageUrl = imageUrl;
        this.txt = txt;
        this.likeCnt = likeCnt;
    }

    int likeCnt;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }
}

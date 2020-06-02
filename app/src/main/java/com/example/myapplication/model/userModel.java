package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class userModel {
    @SerializedName("macaddr")
    String macaddr = "";
    String token   = "";
    String email   = "";
    String yn_use  = "";
    String c_date  = "";

    public userModel(String macaddr, String email) {
        this.macaddr = macaddr;
        this.email = email;
    }

    public String getMacaddr() {
        return macaddr;
    }

    public void setMacaddr(String macaddr) {
        this.macaddr = macaddr;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYn_use() {
        return yn_use;
    }

    public void setYn_use(String yn_use) {
        this.yn_use = yn_use;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}

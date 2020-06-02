package com.example.myapplication.model;

public abstract class baseModel {
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    String tableName = "";
}

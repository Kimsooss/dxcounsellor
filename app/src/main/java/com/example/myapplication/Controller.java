package com.example.myapplication;

import com.example.myapplication.interf.CommonAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    String BASE_URL = "http://10.181.14.45:5000/";

    private static Controller ct = new Controller();
    public static Controller getInstance(){
        return ct;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    CommonAPI service = retrofit.create(CommonAPI.class);

    public CommonAPI getService() {
        return ct.service;
    }
    public CommonAPI callService() {
        return ct.service;
    }

}

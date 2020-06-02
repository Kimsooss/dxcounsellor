package com.example.myapplication.rest;

import com.example.myapplication.R;
import com.example.myapplication.callback.CommonCallback;
import com.example.myapplication.interf.CommonAPI;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller extends CommonCallback {
    private Retrofit mRetrofit;
    private String baseUrl;
    private CommonAPI mApi;


    public Controller(){

    }

    public Retrofit getmRetrofit() {
        return mRetrofit;

    }

    public void setmRetrofit() {
//    mRetrofit = new Retrofit.Builder().baseUrl(String.).addConverterFactory(GsonConverterFactory.create()).build();

    }

    @Override
    public void onResponse(Call call, Response response) {

    }

    @Override
    public void onFailure(Call call, Throwable t) {

    }
}

package com.cyt.demoforstructor.network;

import com.cyt.demoforstructor.bean.GankBean;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GankApi {

    @GET("api/data/Android/10/1")
    Call<GankBean> getAndroidInfo();
}

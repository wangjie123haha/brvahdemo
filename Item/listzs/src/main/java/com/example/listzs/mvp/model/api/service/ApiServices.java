package com.example.listzs.mvp.model.api.service;

import com.example.listzs.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServices {

    //展示
    @GET("api/data/福利/10/1")
    Observable<User> GetObsearvable();
}

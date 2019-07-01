package com.example.duotiaomu.mvp.model.api.service;

import com.example.duotiaomu.bean.News;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiServices {

    @GET("api/news/news.php?page=")
    Observable<News> GetNews(@QueryMap HashMap<String,Integer> hashMap);

}

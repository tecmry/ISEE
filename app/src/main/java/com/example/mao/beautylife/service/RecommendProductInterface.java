package com.example.mao.beautylife.service;

import com.example.mao.beautylife.data.NetVideoItemData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Tecmry on 2018/3/26.
 */

public interface RecommendProductInterface {
    @GET("/product/ls")
    Call<NetVideoItemData> listRespnse(@Query("currentPage")int page);
}

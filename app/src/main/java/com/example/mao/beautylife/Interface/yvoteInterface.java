package com.example.mao.beautylife.Interface;

import com.example.mao.beautylife.beans.VoteData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Tecmry on 2017/12/9.
 */

public interface yvoteInterface {
    @GET("d")
    Call<VoteData> getCount();
}

package com.example.almal.brainapp;

/**
 * Created by almal on 2017-03-29.
 */

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {
    @GET("kandisar")
    Call<String> getTopStars(@Query("api_key") String apiKey);
}
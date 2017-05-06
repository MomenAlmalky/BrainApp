package com.example.almal.brainapp;

import retrofit2.Retrofit;

/**
 * Created by almal on 2017-03-27.
 */

public class Service {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    public static Retrofit getService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.posh24.se/")
                .build();
        return retrofit;
    }

}

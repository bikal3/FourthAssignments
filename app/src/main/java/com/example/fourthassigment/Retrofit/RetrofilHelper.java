package com.example.fourthassigment.Retrofit;

import com.example.fourthassigment.Retrofit.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofilHelper {
    private static Retrofit retrofit;


    public static Retrofit instance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}

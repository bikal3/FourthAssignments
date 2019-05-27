package com.example.fourthassigment;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.fourthassigment.Url.BASE_URL;

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

package com.example.fourthassigment.API;

import com.example.fourthassigment.Model.LoginRegisterResponse;
import com.example.fourthassigment.Model.ItemModel;
import com.example.fourthassigment.Model.ItemsModel;
import com.example.fourthassigment.Model.UserModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface HeroAPI {

    //using object
    @POST("heroes")
    Call<Void> addHero(@Body ItemModel itemModel);
    @FormUrlEncoded
    @POST("login")
    Call<LoginRegisterResponse> checkUser(@Field("username") String username,@Field("password") String password);

    @FormUrlEncoded
    @POST("users/signup")
    Call<LoginRegisterResponse> registerUser(@Field("username")String username,@Field("password") String password);

    @POST("insertitem")
    Call<Void>addItem(@Body ItemsModel itemsModel);

    @POST("registration")
    Call<Void>addUser(@Body UserModel userModel);

}

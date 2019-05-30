package com.example.fourthassigment.API;

import com.example.fourthassigment.Model.ImageModel;
import com.example.fourthassigment.Model.ItemsModel;
import com.example.fourthassigment.Model.LoginRegisterResponse;
import com.example.fourthassigment.Model.UserModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HeroAPI {


    @FormUrlEncoded
    @POST("login")
    Call<LoginRegisterResponse> checkUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("users/signup")
    Call<LoginRegisterResponse> registerUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST ("login")
    Call<String> login(@Field("username") String user,@Field("password")String pass);

    @POST("insertitem")
    Call<Void> addItem(@Body ItemsModel itemsModel);

    @POST("registration")
    Call<Void> addUser(@Body UserModel userModel);

    @GET("selectitem")
    Call<List<ItemsModel>> getAllData();

    @Multipart
    @POST("upload")
    Call<String> uploadImage(@Part MultipartBody.Part body);

}

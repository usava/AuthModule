package com.example.cowboy.authmodule.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Cowboy on 16.12.2017.
 */

public interface ApiService {
    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @GET("users/{username}/repos")
    Observable<JsonArray> getRepos(@Path("username") String username);

    @FormUrlEncoded
    @POST("authenticate")
    Observable<JsonObject> login(@Field("email") String email, @Field("pass") String pass);
}

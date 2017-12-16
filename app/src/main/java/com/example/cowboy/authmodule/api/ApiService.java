package com.example.cowboy.authmodule.api;

import com.google.gson.JsonArray;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Cowboy on 16.12.2017.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("")
    Observable<JsonArray> signIn(@Field("email") String email, @Field("password") String password);
}

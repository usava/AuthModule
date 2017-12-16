package com.example.cowboy.authmodule.auth;

import com.example.cowboy.authmodule.api.ApiService;
import com.example.cowboy.authmodule.common.BaseInteractor;
import com.example.cowboy.authmodule.common.IInteractorContract;
import com.google.gson.JsonArray;

import rx.Observable;

/**
 * Created by Cowboy on 16.12.2017.
 */

public class AuthInteractorImpl extends BaseInteractor<IInteractorContract.IAuthInteractor> implements IInteractorContract.IAuthInteractor {


    public AuthInteractorImpl(ApiService apiService) {
        this.api = apiService;
    }

    @Override
    public Observable<JsonArray> requestSignIn(String email, String password) {
        return api.signIn(email, password).doOnSubscribe().doOnCompleted();
    }

    @Override
    public Observable<String> requestSignUp(String email, String phone, String password) {
        return Observable.just("");
    }

    @Override
    public Observable<String> requestForgotPassword(String email) {
        return Observable.just("");
    }
}
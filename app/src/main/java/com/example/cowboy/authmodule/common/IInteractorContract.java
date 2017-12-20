package com.example.cowboy.authmodule.common;

import com.google.gson.JsonArray;

import rx.Observable;

/**
 * Created by Cowboy on 16.12.2017.
 */

public interface IInteractorContract {

    interface IAuthInteractor extends IInteractorContract {

        Observable<JsonArray> getRepos(String username);
        Observable<String> requestSignUp(String email, String phone, String password);
        Observable<String> requestForgotPassword(String email);
    }
}

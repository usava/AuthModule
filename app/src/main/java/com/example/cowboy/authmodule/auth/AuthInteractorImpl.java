package com.example.cowboy.authmodule.auth;

import android.util.Log;

import com.example.cowboy.authmodule.api.ApiService;
import com.example.cowboy.authmodule.common.BaseInteractor;
import com.example.cowboy.authmodule.common.IInteractorContract;
import com.google.gson.JsonObject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Cowboy on 16.12.2017.
 */

public class AuthInteractorImpl extends BaseInteractor<IInteractorContract.IAuthInteractor> implements IInteractorContract.IAuthInteractor {


    public AuthInteractorImpl(ApiService apiService) {
        this.api = apiService;
    }

    @Override
    public Observable<JsonObject> requestSignIn(String email, String password) {
        String key = "rNkJGSL1sg@Jbz@iFWV8|4fB5lP{n#Z%HGGQtQOb";
        password = MD5(password);
        return api.login(email, password, key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable ->
                    Log.d("slava", " interactor dooneror "+throwable.getMessage())
                )
                .doOnNext(next -> Log.d("slava", " onnext "+next.toString()))
                .doOnCompleted(()->Log.d("slava", " oncomplete "));
    }

    @Override
    public Observable<String> requestSignUp(String email, String phone, String password) {
        return Observable.just("");
    }

    @Override
    public Observable<String> requestForgotPassword(String email) {
        return Observable.just("");
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}

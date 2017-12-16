package com.example.cowboy.authmodule.auth.auth_di;

import android.app.Application;

import com.example.cowboy.authmodule.api.ApiService;
import com.example.cowboy.authmodule.auth.AuthInteractorImpl;
import com.example.cowboy.authmodule.auth.AuthPresenterImpl;
import com.example.cowboy.authmodule.common.IInteractorContract;
import com.example.cowboy.authmodule.common.IPresenterContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Cowboy on 16.12.2017.
 */

@Module

public class AuthModule
{
    @Provides
    @AuthScope
    IPresenterContract.IAuthPresenter provideAuthPresenter(Application application){
        return new AuthPresenterImpl(application);
    }

    @Provides
    @AuthScope
    IInteractorContract.IAuthInteractor provideAuthInteractor(ApiService apiService){
        return new AuthInteractorImpl(apiService);
    }


}

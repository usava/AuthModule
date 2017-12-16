package com.example.cowboy.authmodule.app;

import android.app.Application;
import android.content.Context;

import com.example.cowboy.authmodule.app.app_di.AppComponent;
import com.example.cowboy.authmodule.app.app_di.AppModule;
import com.example.cowboy.authmodule.app.app_di.DaggerAppComponent;

/**
 * Created by Cowboy on 15.12.2017.
 */

public class AuthModuleApp extends Application
{
    private AppComponent appComponent;

    public static AuthModuleApp get(Context context){
        return (AuthModuleApp) context.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){ return appComponent;}
}

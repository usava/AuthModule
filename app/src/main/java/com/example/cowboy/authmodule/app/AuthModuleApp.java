package com.example.cowboy.authmodule.app;

import android.app.Application;
import android.content.Context;

import com.example.cowboy.authmodule.app.app_di.ApiModule;
import com.example.cowboy.authmodule.app.app_di.AppComponent;
import com.example.cowboy.authmodule.app.app_di.AppModule;
import com.example.cowboy.authmodule.app.app_di.DaggerAppComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

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

        initRealmConfiguration();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .apiModule(new ApiModule())
                .build();
    }

    public AppComponent getAppComponent(){ return appComponent;}

    private void initRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}

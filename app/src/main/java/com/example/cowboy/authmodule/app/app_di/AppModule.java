package com.example.cowboy.authmodule.app.app_di;

import android.app.Application;

import com.example.cowboy.authmodule.realm.IRealmService;
import com.example.cowboy.authmodule.realm.RealmService;
import com.example.cowboy.authmodule.utils.NetworkCheckImpl;
import com.example.cowboy.authmodule.utils.Validator;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by Cowboy on 15.12.2017.
 */

@Module
public class AppModule
{
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @AppScope
    public Application provideApplication(){return application;}

    @Provides
    @AppScope
    public Validator provideValidator(){return new Validator();}

    @Provides
    @AppScope
    public NetworkCheckImpl provideNetworkCheck(){return new NetworkCheckImpl(application);}

    @Provides
    @AppScope
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @AppScope
    IRealmService provideRealmService(final Realm realm) {
        return new RealmService(realm);
    }
}

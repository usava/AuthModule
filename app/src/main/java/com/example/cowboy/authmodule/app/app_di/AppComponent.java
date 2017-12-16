package com.example.cowboy.authmodule.app.app_di;

import com.example.cowboy.authmodule.auth.auth_di.AuthComponent;
import com.example.cowboy.authmodule.auth.auth_di.AuthModule;
import com.example.cowboy.authmodule.main.main_di.MainComponent;
import com.example.cowboy.authmodule.main.main_di.MainModule;

import dagger.Component;

/**
 * Created by Cowboy on 15.12.2017.
 */

@AppScope
@Component(modules = {AppModule.class})

public interface AppComponent {
    MainComponent plus(MainModule module);
    AuthComponent plus(AuthModule module);
}

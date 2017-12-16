package com.example.cowboy.authmodule.auth.auth_di;

import com.example.cowboy.authmodule.auth.AuthActivity;

import dagger.Subcomponent;

/**
 * Created by Cowboy on 16.12.2017.
 */

@AuthScope
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent {
    void inject(AuthActivity activity);
}

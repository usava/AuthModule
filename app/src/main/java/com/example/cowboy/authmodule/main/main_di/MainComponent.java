package com.example.cowboy.authmodule.main.main_di;

import com.example.cowboy.authmodule.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Cowboy on 15.12.2017.
 */

@MainScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}

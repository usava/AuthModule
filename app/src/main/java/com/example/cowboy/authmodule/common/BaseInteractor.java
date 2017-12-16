package com.example.cowboy.authmodule.common;

import com.example.cowboy.authmodule.api.ApiService;

/**
 * Created by Cowboy on 16.12.2017.
 */

public abstract class BaseInteractor<I extends IInteractorContract> {
    protected ApiService api;
}


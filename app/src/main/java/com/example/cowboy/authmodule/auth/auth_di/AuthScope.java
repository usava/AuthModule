package com.example.cowboy.authmodule.auth.auth_di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Cowboy on 16.12.2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
@interface AuthScope {
}

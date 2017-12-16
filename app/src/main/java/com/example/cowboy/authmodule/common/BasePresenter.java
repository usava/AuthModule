package com.example.cowboy.authmodule.common;

import android.app.Application;

import com.example.cowboy.authmodule.realm.IRealmService;
import com.example.cowboy.authmodule.utils.INetworkCheck;
import com.example.cowboy.authmodule.utils.IValidator;

/**
 * Created by Cowboy on 15.12.2017.
 */

public abstract class BasePresenter<V extends IBaseView, I extends IInteractorContract>
{
    protected V view;
    protected  I interactor;
    protected IRealmService realmService;
    protected IValidator validator;
    protected INetworkCheck networkCheck;
    protected Application application;

    public void init(V view){this.view = view;};
    public void dismiss(){this.view =null;}
}

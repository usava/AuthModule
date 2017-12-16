package com.example.cowboy.authmodule.main;

import android.app.Application;

import com.example.cowboy.authmodule.common.BasePresenter;
import com.example.cowboy.authmodule.common.IBaseView;
import com.example.cowboy.authmodule.common.IPresenterContract;

/**
 * Created by Cowboy on 15.12.2017.
 */

public class MainPresenterImpl extends BasePresenter<IBaseView.IMainView> implements IPresenterContract.IMainPresenter<IBaseView.IMainView> {

    public MainPresenterImpl(Application application) {
        this.application = application;
    }

    @Override
    public void showMain() {

    }

    @Override
    public void init(IBaseView.IMainView view){
        super.init(view);
    }
    @Override
    public void dismiss(){
        super.dismiss();
    }
}

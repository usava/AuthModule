package com.example.cowboy.authmodule.auth;

import android.app.Application;
import android.util.Log;

import com.example.cowboy.authmodule.common.BasePresenter;
import com.example.cowboy.authmodule.common.IBaseView;
import com.example.cowboy.authmodule.common.IInteractorContract;
import com.example.cowboy.authmodule.common.IPresenterContract;
import com.example.cowboy.authmodule.realm.IRealmService;
import com.example.cowboy.authmodule.utils.INetworkCheck;
import com.example.cowboy.authmodule.utils.IValidator;

import javax.inject.Inject;

/**
 * Created by Cowboy on 16.12.2017.
 */

public class AuthPresenterImpl extends BasePresenter<IBaseView.IAuthView, IInteractorContract.IAuthInteractor> implements IPresenterContract.IAuthPresenter {

    @Inject
    public AuthPresenterImpl(IInteractorContract.IAuthInteractor interactor, IRealmService realmService,
                             IValidator validator, INetworkCheck networkCheck, Application application) {

        this.interactor = interactor;
        this.application = application;
        this.validator = validator;
        this.realmService = realmService;
        this.networkCheck = networkCheck;
    }

    @Override
    public void init(IBaseView.IAuthView view) {
        super.init(view);
    }

    @Override
    public void doSignIn(String username) {
        interactor.getRepos(username).doOnRequest(mLong -> view.showProgress()).subscribe(
                next ->{
                    view.showError(next.toString());
                    Log.d("slava", " next "+next.toString());
                }, throwable -> {
                    view.showError(throwable.getMessage());
                    Log.d("slava", " error "+throwable.getMessage());
                }, () -> {
                    view.hideProgress();
                }
        );
    }

    @Override
    public void doSignUp(String email, String phone, String password) {

    }

    @Override
    public void doFrogotPassword(String email) {

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

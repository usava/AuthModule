package com.example.cowboy.authmodule.common;

/**
 * Created by Cowboy on 15.12.2017.
 */

public interface IPresenterContract
{
    void dismiss();

    interface IMainPresenter<V extends IBaseView.IMainView> extends IPresenterContract{
        void init(V view);
        void showMain();
    }

    interface IAuthPresenter<V extends IBaseView.IAuthView> extends IPresenterContract{
        void init(V view);
        void doSignIn(String username);
        void doSignUp(String email, String phone, String password);
        void doFrogotPassword(String email);
    }
}

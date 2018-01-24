package com.example.cowboy.authmodule.common;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.widget.EditText;

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
        void doSignIn(String email, String password);
        void doSignUp(String email, String phone, String password);
        void doForgotPassword(String email);

        void doValidateSign(EditText etEmail, EditText etPassword, EditText etName, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag);
    }
}

package com.example.cowboy.authmodule.common;

/**
 * Created by Cowboy on 15.12.2017.
 */

public interface IBaseView
{
    void hideProgress();
    void showProgress();
    void showError(String s);

    interface IMainView extends IBaseView{
        void onSuccess();
    }

    interface IAuthView extends IBaseView{
        @Override
        public void hideProgress();

        @Override
        public void showProgress();

        @Override
        public void showError(String s);

        void onAuth();
    }
}

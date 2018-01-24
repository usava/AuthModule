package com.example.cowboy.authmodule.auth;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.cowboy.authmodule.common.BasePresenter;
import com.example.cowboy.authmodule.common.IBaseView;
import com.example.cowboy.authmodule.common.IInteractorContract;
import com.example.cowboy.authmodule.common.IPresenterContract;
import com.example.cowboy.authmodule.realm.IRealmService;
import com.example.cowboy.authmodule.utils.INetworkCheck;
import com.example.cowboy.authmodule.utils.IValidator;
import com.jakewharton.rxbinding.widget.RxTextView;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    private String TAG = "AuthImplement";

    @Override
    public void init(IBaseView.IAuthView view) {
        super.init(view);
    }

    @Override
    public void doSignIn(String email, String password) {
        interactor.requestSignIn(email, password).doOnRequest(mLong -> view.showProgress()).subscribe(
                next -> {
                    view.showError(next.toString());
                    Log.d("slava", " next " + next.toString());
                }, throwable -> {
                    view.showError(throwable.getMessage());
                    Log.d("slava", " error " + throwable.getMessage());
                }, () -> {
                    view.hideProgress();
                }
        );
    }

    @Override
    public void doSignUp(String email, String phone, String password) {

    }

    @Override
    public void doForgotPassword(String email) {

    }

    @Override
    public void doValidateSign(EditText etEmail, EditText etPassword, EditText etName, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag) {
        Observable<CharSequence> emailObservable = null;
        Observable<CharSequence> passwordObservable = null;
        Observable<CharSequence> nameObservable = null;

        Observable<Boolean> booleanObservable = null;
        if(etEmail != null)
            emailObservable = RxTextView.textChanges(etEmail);

        if(etPassword != null)
            passwordObservable = RxTextView.textChanges(etPassword);

        if(etName != null)
            nameObservable = RxTextView.textChanges(etName);


        switch (flag){
            //SignIn
            case 1:
                emailObservable
                        .map(email -> AuthPresenterImpl.this.validator.isEmailValid(email))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 1email" + e.getMessage()))
                        .subscribe(isValid -> etEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (isValid ? drwValid : drwNotValid), null));

                passwordObservable
                        .map(password -> AuthPresenterImpl.this.validator.isPasswordValid(password))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 1password" + e.getMessage()))
                        .subscribe(isValid -> etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (isValid ? drwValid : drwNotValid), null));

                booleanObservable = Observable.combineLatest(emailObservable, passwordObservable, (o1, o2)
                        -> AuthPresenterImpl.this.validator.isEmailValid(o1) && AuthPresenterImpl.this.validator.isPasswordValid(o2));

                booleanObservable
                        .doOnError(Throwable::printStackTrace)
                        .subscribe(is -> {
                    btn.setVisibility(is ? View.VISIBLE : View.GONE);
                }, throwable -> throwable.getMessage());
                break;
            //SignUp
            case 2:
                emailObservable
                        .map(email -> AuthPresenterImpl.this.validator.isEmailValid(email))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 2email" + e.getMessage()))
                        .subscribe(isValid -> etEmail.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (isValid ? drwValid : drwNotValid), null));

                passwordObservable = RxTextView.textChanges(etPassword);
                passwordObservable
                        .map(password -> AuthPresenterImpl.this.validator.isPasswordValid(password))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 2password" + e.getMessage()))
                        .subscribe(isValid -> etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (isValid ? drwValid : drwNotValid), null));

                nameObservable
                        .map(username -> AuthPresenterImpl.this.validator.isUsernameValid(username))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 2name" + e.getMessage()))
                        .subscribe(isValid -> etName.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, (isValid ? drwValid : drwNotValid), null));

                booleanObservable = Observable.combineLatest(emailObservable, passwordObservable, nameObservable, (o1, o2, o3)
                        -> AuthPresenterImpl.this.validator.isEmailValid(o1) && AuthPresenterImpl.this.validator.isPasswordValid(o2) && AuthPresenterImpl.this.validator.isUsernameValid(o3));
                booleanObservable
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 2boolean" + e.getMessage()))
                        .subscribe(is -> {
                    btn.setVisibility(is ? View.VISIBLE : View.GONE);
                });
            //Forgot Password
            case 3:
                emailObservable
                        .map(email -> AuthPresenterImpl.this.validator.isEmailValid(email))
                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 3email" + e.getMessage()))
                        .subscribe(new Subscriber<Boolean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "VALIDATION_SIGN ====onError() 3email subscribe -- " + e.getMessage());
                            }

                            @Override
                            public void onNext(Boolean s) {
                                Observable<Boolean> observable = Observable.just(s);
                                observable
                                        .doOnError(e -> Log.d(TAG, "VALIDATION_SIGN ====onError() 3email onNext " + e.getMessage()))
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(isVisible -> btn.setVisibility(isVisible ? View.VISIBLE : View.GONE));
                            }
                        });
                break;
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}

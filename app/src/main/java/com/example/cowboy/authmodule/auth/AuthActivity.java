package com.example.cowboy.authmodule.auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.app.AuthModuleApp;
import com.example.cowboy.authmodule.auth.auth_di.AuthModule;
import com.example.cowboy.authmodule.auth.fragments.SignInFragment;
import com.example.cowboy.authmodule.common.IBaseView;
import com.example.cowboy.authmodule.common.IPresenterContract;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity implements IBaseView.IAuthView {

    @Inject
    IPresenterContract.IAuthPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        ((AuthModuleApp) getApplication()).getAppComponent().plus(new AuthModule()).inject(this);
        presenter.init(this);

        IAuthListener authListener = new IAuthListener() {
            @Override
            public void signUp(String login, String email, String password) {

            }

            @Override
            public void signIn(String login, String password) {
                presenter.doSignIn(login);
                Log.d("login ", login);
            }

            @Override
            public void openSignUp() {
                Toast.makeText(getApplicationContext(), "openSignUp", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void openSignIn() {

            }

            @Override
            public void openDialogForgotPassword() {
                Toast.makeText(getApplicationContext(), "openForgot", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void forgotPassword() {
                Toast.makeText(getApplicationContext(), "sendForgot", Toast.LENGTH_SHORT).show();
            }
        };

        getSupportFragmentManager().beginTransaction().add(R.id.auth_container, SignInFragment.newInstance(authListener)).commit();
    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuth() {

    }



    public interface IAuthListener {
        void signUp(String login, String email, String password);
        void signIn(String login, String password);
        void openSignUp();
        void openSignIn();
        void openDialogForgotPassword();
        void forgotPassword();
    }
}

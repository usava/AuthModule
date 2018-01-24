package com.example.cowboy.authmodule.auth;

import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.app.AuthModuleApp;
import com.example.cowboy.authmodule.auth.auth_di.AuthModule;
import com.example.cowboy.authmodule.auth.fragments.SignInFragment;
import com.example.cowboy.authmodule.common.IBaseView;
import com.example.cowboy.authmodule.common.IPresenterContract;
import com.example.cowboy.authmodule.receiver.NetworkMonitor;

import javax.inject.Inject;

public class AuthActivity extends AppCompatActivity implements IBaseView.IAuthView {

    @Inject
    IPresenterContract.IAuthPresenter presenter;

    private NetworkMonitor mNetworkMonitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        mNetworkMonitor = new NetworkMonitor();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkMonitor, intentFilter);

        ((AuthModuleApp) getApplication()).getAppComponent().plus(new AuthModule()).inject(this);
        presenter.init(this);

        IAuthListener authListener = new IAuthListener() {
            @Override
            public void signUp(String login, String email, String password) {}

            @Override
            public void signIn(String email, String password) {
                presenter.doSignIn(email, password);
                Log.d("login password", email+" "+password);
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

            @Override
            public void doSignInValidate(EditText etEmail, EditText etPassword, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag) {
                presenter.doValidateSign(etEmail, etPassword, null, btn, drwValid, drwNotValid, flag);
                Toast.makeText(AuthActivity.this, "doSignInValidate"+etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doSignUpValidate(EditText etEmail, EditText etPassword, EditText etName, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag) {
                presenter.doValidateSign(etEmail, etPassword, null, btn, drwValid, drwNotValid, flag);
                Toast.makeText(AuthActivity.this, "doSignInValidate"+etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doForgotPasswordValidate(EditText etEmail, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag) {
                presenter.doValidateSign(etEmail, null, null, btn, drwValid, drwNotValid, flag);
                Toast.makeText(AuthActivity.this, "doSignInValidate"+etEmail.getText().toString(), Toast.LENGTH_SHORT).show();
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
        void doSignInValidate(EditText etEmail, EditText etPassword, AppCompatButton btn, Drawable drwvalid, Drawable drwNotValid, int flag);
        void doSignUpValidate(EditText etEmail, EditText etPassword, EditText etName, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag);
        void doForgotPasswordValidate(EditText etEmail, AppCompatButton btn, Drawable drwValid, Drawable drwNotValid, int flag);
    }
}

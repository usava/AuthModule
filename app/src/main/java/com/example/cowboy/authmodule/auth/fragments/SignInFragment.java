package com.example.cowboy.authmodule.auth.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.auth.AuthActivity;

public class SignInFragment extends Fragment {

    private AuthActivity.IAuthListener authListener;
    private Drawable mInvalidField, mValidField;

    public SignInFragment() {
        // Required empty public constructor
    }

    public SignInFragment(AuthActivity.IAuthListener authListener) {
        this.authListener = authListener;
    }

    public static SignInFragment newInstance(AuthActivity.IAuthListener authListener) {
        SignInFragment fragment = new SignInFragment(authListener);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        final EditText etLogin = (EditText) view.findViewById(R.id.et_signin_login);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_signin_password);
        final EditText etUsername = (EditText) view.findViewById(R.id.et_signup_login);

        final TextView tvForgot = (TextView) view.findViewById(R.id.tv_signin_forgot);
        final TextView tvSignUp = (TextView) view.findViewById(R.id.tv_signin_signup);

        final AppCompatButton btnSignIn = (AppCompatButton) view.findViewById(R.id.btn_signin_signin);
        final AppCompatButton btnSignUn = (AppCompatButton) view.findViewById(R.id.btn_signup_signup);
        mValidField = getResources().getDrawable(android.R.drawable.presence_online);
        mInvalidField = getResources().getDrawable(android.R.drawable.presence_busy);
        authListener.doSignInValidate(etLogin, etPassword, btnSignIn, mValidField, mInvalidField, 1);
        authListener.doSignUpValidate(etLogin, etPassword, etUsername, btnSignUn, mValidField, mInvalidField, 1);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authListener.openSignUp();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authListener.signIn(etLogin.getText().toString(), etPassword.getText().toString());
            }
        });

        tvForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authListener.openDialogForgotPassword();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

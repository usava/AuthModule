package com.example.cowboy.authmodule.auth.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.auth.AuthActivity;

public class SignInFragment extends Fragment {

    private AuthActivity.IAuthListener authListener;

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

        final TextView tvForgot = (TextView) view.findViewById(R.id.tv_signin_forgot);
        final TextView tvSignUp = (TextView) view.findViewById(R.id.tv_signin_signup);
        final Button btnSugnIn = (Button) view.findViewById(R.id.btn_signin_signin);

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authListener.openSignUp();
            }
        });
        btnSugnIn.setOnClickListener(new View.OnClickListener() {
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

package com.example.cowboy.authmodule.auth.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.auth.AuthActivity;

public class SignUpFragment extends Fragment {
    private AuthActivity.IAuthListener authListener;

    public SignUpFragment() {
        // Required empty public constructor
    }

    public SignUpFragment(AuthActivity.IAuthListener authListener) {
        this.authListener = authListener;
    }

    public static SignUpFragment newInstance(AuthActivity.IAuthListener authListener) {
        SignUpFragment fragment = new SignUpFragment(authListener);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        final EditText etEmail = (EditText) view.findViewById(R.id.et_signup_email);
        final EditText etLogin = (EditText) view.findViewById(R.id.et_signup_login);
        final EditText etPassword = (EditText) view.findViewById(R.id.et_signup_password);

        final TextView tvForgot = (TextView) view.findViewById(R.id.tv_signup_forgot);
        final TextView tvSingIn = (TextView) view.findViewById(R.id.tv_signup_signin);

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

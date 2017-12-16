package com.example.cowboy.authmodule.auth.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.cowboy.authmodule.R;
import com.example.cowboy.authmodule.auth.AuthActivity;

import butterknife.ButterKnife;

/**
 * Created by Cowboy on 15.12.2017.
 */

public class ForgotPasswordDialog extends DialogFragment
{
    private AuthActivity.IAuthListener authListener;

    public ForgotPasswordDialog(AuthActivity.IAuthListener authListener) {
        this.authListener = authListener;
    }

    public static ForgotPasswordDialog newInstance(AuthActivity.IAuthListener authListener) {
        ForgotPasswordDialog dialog = new ForgotPasswordDialog(authListener);
        return dialog;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dialog_forgot, null);
        ButterKnife.bind(this, v);
        authListener.openDialogForgotPassword();
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        return v;
    }

    public void onDialogCancel() {
        this.dismiss();
        authListener.openDialogForgotPassword();
    }
}

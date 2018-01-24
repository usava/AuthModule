package com.example.cowboy.authmodule.utils;

/**
 * Created by Cowboy on 16.12.2017.
 */

import android.text.TextUtils;

public class Validator implements IValidator{

    public Validator() {}
    @Override
    public boolean isUsernameValid(CharSequence username) {
        return !TextUtils.isEmpty(username);
    }


    @Override
    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    @Override
    public boolean isPhoneValid(CharSequence phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
    @Override
    public boolean isPasswordValid(CharSequence value) {
        return value.toString().matches("^(?=.*\\d).{4,8}$");
    }
    @Override
    public boolean isCodeValid(CharSequence code) {
        return code.toString().matches("[0-9]{5}");
    }
}
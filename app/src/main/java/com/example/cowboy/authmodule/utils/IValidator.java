package com.example.cowboy.authmodule.utils;

/**
 * Created by Cowboy on 16.12.2017.
 */

public interface IValidator {

    boolean validUsername(String username);

    boolean isEmailValid(CharSequence email);

    boolean isPhoneValid(CharSequence phone);

    boolean isPasswordValid(CharSequence value);

    boolean isCodeValid(CharSequence code);
}

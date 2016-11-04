package com.apolloapps.theledger.Utils;

import android.app.ActivityManager;

import com.apolloapps.theledger.Common.AppConstants;
import com.example.UserValidator;
import com.nineoldandroids.view.animation.AnimatorProxy;

/**
 * Created by AMoreira on 6/28/16.
 */
public class Validator {

    private UserValidator mValidator;

    public Validator(){
        mValidator = UserValidator.builder()
                .emailPattern(AppConstants.EMAIL_PATTERN)
                .passwordMaximumLength(AppConstants.PASSWORD_MAXIMUM_LENGTH)
                .passwordMinimumLength(AppConstants.PASSWORD_MINIMUM_LENGTH)
                .passwordPattern(AppConstants.PASSWORD_PATTERN)
                .usernameMaximumLength(AppConstants.USERNAME_MAXIMUM_LENGTH)
                .usernameMinimumLength(AppConstants.USERNAME_MINIMUM_LENGTH)
                .usernamePattern(AppConstants.USERNAME_PATTERN)
                .build();
    }

    public boolean validateEmail(String email) {
        try {
            return mValidator.validateEmail(email);

        } catch (RuntimeException e){
            return false;
        }

    }

    public boolean validateUsername(String username) {
        try
        {
            return mValidator.validateUsername(username);
        } catch (RuntimeException e) {
            return false;
        }


    }

    public boolean validatePassword(String password){
        try{
            return mValidator.validatePassword(null,password);
        } catch (RuntimeException e) {
            return false;
        }

    }


}

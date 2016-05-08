package com.apolloapps.theledger.Common;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by AMoreira on 5/7/16.
 */
public class NetworkConstants {

    //Network Request Constants
    public static final int DEFAULT_TIMEOUT_MS = 60000;
    public static final int MAX_NUM_RETRIES = 0;
    public static final float DEFAULT_BACKOFF_MULT = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;


    //URL and EndPoints
    public static final String BASE_URL = "http://10.0.2.2:8080";
    public static final String CREATE_ACCOUNT = "/UserAccount/CreateAccount";
    public static final String GET_ACCOUNT_DETAILS = "/UserAccount";

    //JSON keys
    public static final String PARAM_FIRST_NAME = "firstName";
    public static final String PARAM_LAST_NAME = "lastName";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_PHONE_NUMBER = "phoneNumber";
    public static final String PARAM_EMAIL = "emailAddress";
}

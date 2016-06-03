package com.apolloapps.theledger.Common;

import com.android.volley.DefaultRetryPolicy;

/**
 * Created by AMoreira on 5/7/16.
 */
public class NetworkConstants {

    //Network Request Constants
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    public static final int MAX_NUM_RETRIES = 0;
    public static final float DEFAULT_BACKOFF_MULT = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;

    //Status Codes
    public static final int STATUS_0 = 0;
    public static final int STATUS_200 = 200;
    public static final int STATUS_401 = 401;
    public static final int STATUS_500 = 500;

    //URL and EndPoints
    public static final String BASE_URL = "http://10.0.2.2:8080";
    public static final String LOGIN = "/UserAccount/Login";
    public static final String USER_CREATE_ACCOUNT = "/UserAccount/CreateAccount";
    public static final String GET_USER_ACCOUNT_DETAILS = "/UserAccount";
    public static final String GET_ACCOUNT_LIST = "/Account";
    public static final String CREATE_ACCOUNT = "/Account/CreateAccount";
    public static final String DELETE_ACCOUNT = "/Account/DeleteAccount";
    public static final String UPDATE_ACCOUNT = "/Account/UpdateAccount";

    //JSON keys
    //Global
    public static final String PARAM_USER_ID = "accountUserId";

    //User Account
    public static final String PARAM_FIRST_NAME = "firstName";
    public static final String PARAM_LAST_NAME = "lastName";
    public static final String PARAM_USERNAME = "username";
    public static final String PARAM_PASSWORD = "password";
    public static final String PARAM_PHONE_NUMBER = "phoneNumber";
    public static final String PARAM_EMAIL = "emailAddress";

    //Feature Account
    public static final String PARAM_ACCOUNT_TITLE = "accountTitle";
    public static final String PARAM_ACCOUNT_USERNAME = "accountUsername";
    public static final String PARAM_ACCOUNT_PASSWORD = "accountPassword";
    public static final String PARAM_ACCOUNT_TYPE = "accountType";
    public static final String PARAM_ACCOUNT_COMMENTS = "accountComments";

}


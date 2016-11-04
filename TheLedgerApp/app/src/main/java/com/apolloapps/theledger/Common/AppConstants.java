package com.apolloapps.theledger.Common;

/**
 * Created by AMoreira on 4/5/16.
 */
public class AppConstants {

    public static final int THREE_SECONDS = 3000;
    public static final int DEFAULT_INT_RETURN = -9999;
    public static final String ACTION = "Action";
    public static final int CREATE_ACCOUNT = 1;
    public static final int EDIT_ACCOUNT = 2;
    public static final String GOOGLE_APP = "com.google.android.googlequicksearchbox";

    public static final int FEATURE_TYPE_ACCOUNT = 1;
    public static final int FEATURE_TYPE_BILLS = 2;
    public static final int FEATURE_TYPE_CHECK_LIST = 3;

    public static final int TODO_CHECKLIST = 0;
    public static final int COMPLETED_CHECKLIST = 1;

    public static final int OTHER_ACCOUNT_TYPE = 0;
    public static final int SOCIAL_ACCOUNT_TYPE = 1;
    public static final int FINANCIAL_ACCOUNT_TYPE = 2;
    public static final int ENTERTAINMENT_ACCOUNT_TYPE = 3;

    public static final int NO_NETWORK_SCREEN = 0;
    public static final int NO_DATA_SERVER_ERROR_SCREEN = 1;
    public static final int NO_DATA = 0;
    public static final int SERVER_ERROR = 1;

    public static final int NO_ACCOUNT = -1;

    public static final String EMAIL_PATTERN = "[a-zA-Z0-9\\+\\._%\\-\\+]{1,256}" +
            "@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{1,25}" +
            ")+";
    public static final String USERNAME_PATTERN = "^([-_A-Za-z0-9])*$";
    public static final int USERNAME_MINIMUM_LENGTH = 3;
    public static final int USERNAME_MAXIMUM_LENGTH = 20;
    public static final int PASSWORD_MINIMUM_LENGTH = 5;
    public static final int PASSWORD_MAXIMUM_LENGTH = 20;
    public static final String PASSWORD_PATTERN = "^([A-Za-z0-9_.,&%€@#~])*$";

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String UTC_TIMEZONE = "UTC";

}

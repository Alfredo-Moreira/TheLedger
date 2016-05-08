package com.apolloapps.theledger.DataManager.Utilities;

/**
 * Created by AMoreira on 5/7/16.
 */
public class NetworkError {
    private int mErrorCode;
    private int mStatusCode;
    private String mErrorMessage;


    public NetworkError(int code,int statusCode, String message){
        mErrorCode = code;
        mStatusCode = statusCode;
        mErrorMessage = message;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public void setErrorCode(int mErrorCode) {
        this.mErrorCode = mErrorCode;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public void setWrrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }

    public int getStatusCode() {
        return mStatusCode;
    }

    public void setStatusCode(int mStatusCode) {
        this.mStatusCode = mStatusCode;
    }
}

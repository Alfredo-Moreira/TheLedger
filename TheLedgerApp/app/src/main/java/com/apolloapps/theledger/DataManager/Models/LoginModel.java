package com.apolloapps.theledger.DataManager.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AMoreira on 4/8/16.
 */
public class LoginModel {

    @SerializedName("UserId")
    private int mUserId;

    @SerializedName("AccessToken")
    private String mAccessToken;

}

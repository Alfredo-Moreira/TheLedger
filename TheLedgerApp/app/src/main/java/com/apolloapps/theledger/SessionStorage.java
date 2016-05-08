package com.apolloapps.theledger;

<<<<<<< HEAD
import android.content.Context;

/**
 * Created by AMoreira on 5/8/16.
 */
public class SessionStorage {

    private int mUserId;
    private Context mContext;


    public SessionStorage(Context context) {
        mContext = context;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }


    public void clearSession(){
        mUserId = -1;
        mContext =null;
=======
/**
 * Created by AMoreira on 4/25/16.
 */
public class SessionStorage {
    private int mUserId;
    private String mAccessToken;

    public SessionStorage(){

    }

    public void startSessionTimer(){

    }

    public void clearSession(){

>>>>>>> origin/develop
    }
}

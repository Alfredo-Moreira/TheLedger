package com.apolloapps.theledger.DataManager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.apolloapps.theledger.Common.NetworkConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Requests.UserCreateAccountRequest;
import com.apolloapps.theledger.DataManager.Requests.UserGetDetailsRequest;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.apolloapps.theledger.DataManager.Responses.UserGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.Preferences.Preferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.LinkedHashMap;

/**
 * Created by AMoreira on 5/7/16.
 */
public class DataManager {

    private Context mContext;
    private UrlConstructor mUrlConstructor;
    private Preferences mPreferences;
    private String mUrl;
    private JSONObject mJSON;
    private RequestQueue mRequestQueue;
    private LinkedHashMap<String,String> mParams;

    public DataManager(Context context, UrlConstructor urConstructor, Preferences preferences){
        mContext =context;
        mPreferences = preferences;
        mUrlConstructor = urConstructor;
        mRequestQueue = buildRequestQueue(context);
    }

    private NetworkError createNetworkError(VolleyError error) {
        if (error != null && error.getMessage() != null) {
            try {
                JSONObject jError = new JSONObject(error.getMessage());
                com.apolloapps.theledger.DataManager.Utilities.NetworkError ne = new com.apolloapps.theledger.DataManager.Utilities.NetworkError(jError.has("Status") ? jError.getInt("Status") : 0,
                        jError.has("Code") ? jError.getInt("Code") : 0,
                        jError.has("Message") ? jError.getString("Message") : "");
                return ne;
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        if (error != null && error.networkResponse != null) {
            return new NetworkError(error.networkResponse.statusCode,
                    error.networkResponse.statusCode, error.getMessage());
        } else {
            return new NetworkError(0, 0, "Cannot Reach Server");
        }
    }


    //The Methods
    /*
     * Create User Account endpoint: /UserAccount/CreateAccount
     */
    public void doCreateUserAccount(PersonalAccountModel account, final ServiceCallback<UserCreateAccountResponse> listener) {
        listener.onPreExecute();


        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_FIRST_NAME, account.getFirstName());
            mJSON.put(NetworkConstants.PARAM_LAST_NAME, account.getLastName());
            mJSON.put(NetworkConstants.PARAM_USERNAME, account.getUsername());
            mJSON.put(NetworkConstants.PARAM_PASSWORD, account.getPassword());//Need SHA-1 Encryption done
            mJSON.put(NetworkConstants.PARAM_PHONE_NUMBER, account.getPhoneNumber());
            mJSON.put(NetworkConstants.PARAM_EMAIL,account.getEmailAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.CREATE_ACCOUNT);
        UserCreateAccountRequest request = new UserCreateAccountRequest(mUrl, mJSON, new Response.Listener<UserCreateAccountResponse>() {

            @Override
            public void onResponse(UserCreateAccountResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Get User Details endpoint:UserAccount/{id}
     */

    public void doGetUserDetails(int id, final ServiceCallback<UserGetDetailsResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_ACCOUNT_DETAILS,String.valueOf(id));
        mUrl = mUrlConstructor.getFinalUrl(mUrlConstructor.getCompoundURL(mParams));

        UserGetDetailsRequest request = new UserGetDetailsRequest(mUrl, null, new Response.Listener<UserGetDetailsResponse>() {
            @Override
            public void onResponse(UserGetDetailsResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }






    private RequestQueue buildRequestQueue(Context context){

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        return Volley.newRequestQueue(context);

    }

}

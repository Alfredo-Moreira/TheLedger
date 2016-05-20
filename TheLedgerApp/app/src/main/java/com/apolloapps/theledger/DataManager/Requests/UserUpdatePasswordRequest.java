package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.UserUpdatePasswordResponse;
import com.apolloapps.theledger.DataManager.Responses.UserUpdateUsernameResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/10/16.
 */
public class UserUpdatePasswordRequest extends BaseRequest<UserUpdatePasswordResponse> {

    public UserUpdatePasswordRequest(String url, JSONObject requestBody, Response.Listener<UserUpdatePasswordResponse> listener, Response.ErrorListener errorListener) {
        super(Method.PUT, url, requestBody.toString(), listener, errorListener);
    }

    @Override
    protected Response<UserUpdatePasswordResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            UserUpdatePasswordResponse response = gson.fromJson(json,UserUpdatePasswordResponse.class);
            return Response.success(response,null);

        } catch (UnsupportedOperationException uue){
            return Response.error(new ParseError(uue));
        }
    }


}

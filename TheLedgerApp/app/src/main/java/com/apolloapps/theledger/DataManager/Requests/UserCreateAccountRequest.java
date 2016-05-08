package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/7/16.
 */
public class UserCreateAccountRequest extends BaseRequest<UserCreateAccountResponse> {

    public UserCreateAccountRequest(String url, JSONObject requestBody, Response.Listener<UserCreateAccountResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, requestBody.toString(), listener, errorListener);
    }

    @Override
    protected Response<UserCreateAccountResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data);
            Gson gson = new  GsonBuilder().create();
            UserCreateAccountResponse response = gson.fromJson(json,UserCreateAccountResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uee) {
            return Response.error(new ParseError(uee));
        }
    }
}

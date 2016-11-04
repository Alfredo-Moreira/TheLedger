package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.UserGetDetailsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by AMoreira on 5/7/16.
 */
public class UserGetDetailsRequest extends BaseRequest<UserGetDetailsResponse> {

    public UserGetDetailsRequest(String url, Response.Listener<UserGetDetailsResponse> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
    }

    @Override
    protected Response<UserGetDetailsResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            UserGetDetailsResponse response = gson.fromJson(json,UserGetDetailsResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uee) {
            return Response.error(new ParseError(uee));
        }
    }
}

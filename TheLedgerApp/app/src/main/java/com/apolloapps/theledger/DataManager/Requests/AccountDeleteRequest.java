package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountDeleteResponse;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/28/16.
 */
public class AccountDeleteRequest extends BaseRequest<AccountDeleteResponse>{

    public AccountDeleteRequest(String url, Response.Listener<AccountDeleteResponse> listener, Response.ErrorListener errorListener) {
        super(Method.DELETE,url,null,listener,errorListener);
    }


    @Override
    protected Response<AccountDeleteResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            AccountDeleteResponse response = gson.fromJson(json,AccountDeleteResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

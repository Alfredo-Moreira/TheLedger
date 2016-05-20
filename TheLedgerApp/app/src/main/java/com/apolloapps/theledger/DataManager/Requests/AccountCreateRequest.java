package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountCreateResponse;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/10/16.
 */
public class AccountCreateRequest extends BaseRequest<AccountCreateResponse> {

    public AccountCreateRequest(String url, JSONObject requestBody, Response.Listener<AccountCreateResponse> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, requestBody.toString(), listener, errorListener);
    }

    @Override
    protected Response<AccountCreateResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            AccountCreateResponse response = gson.fromJson(json,AccountCreateResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue){
            return Response.error(new ParseError(uue));
        }
    }
}

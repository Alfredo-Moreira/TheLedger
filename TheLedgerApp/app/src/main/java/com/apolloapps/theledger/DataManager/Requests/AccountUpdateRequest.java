package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountDeleteResponse;
import com.apolloapps.theledger.DataManager.Responses.AccountUpdateResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/31/16.
 */
public class AccountUpdateRequest extends BaseRequest<AccountUpdateResponse> {

    public AccountUpdateRequest(String url, JSONObject requestBody, Response.Listener<AccountUpdateResponse> listener, Response.ErrorListener errorListener) {
        super(Method.PUT,url,requestBody.toString(),listener,errorListener);
    }


    @Override
    protected Response<AccountUpdateResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            AccountUpdateResponse response = gson.fromJson(json,AccountUpdateResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.AccountGetListResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/10/16.
 */
public class AccountGetDetailsRequest extends BaseRequest<AccountGetDetailsResponse> {

    public AccountGetDetailsRequest(String url, Response.Listener<AccountGetDetailsResponse> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
    }

    @Override
    protected Response<AccountGetDetailsResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            AccountGetDetailsResponse response = gson.fromJson(json,AccountGetDetailsResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

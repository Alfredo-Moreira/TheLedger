package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountGetListResponse;
import com.apolloapps.theledger.DataManager.Responses.UserUpdatePasswordResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 5/10/16.
 */
public class AccountGetListRequest extends BaseRequest<AccountGetListResponse> {

    public AccountGetListRequest(String url, Response.Listener<AccountGetListResponse> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url,null, listener, errorListener);
    }

    @Override
    protected Response<AccountGetListResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            AccountGetListResponse response = gson.fromJson(json,AccountGetListResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

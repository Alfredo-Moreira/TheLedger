package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountGetListResponse;
import com.apolloapps.theledger.DataManager.Responses.BillGetListResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by AMoreira on 6/30/16.
 */
public class BillGetListRequest extends BaseRequest<BillGetListResponse> {

    public BillGetListRequest(String url, Response.Listener<BillGetListResponse>listener, Response.ErrorListener errorListener){
        super(Method.GET, url,null, listener, errorListener);
    }

    @Override
    protected Response<BillGetListResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            BillGetListResponse response = gson.fromJson(json,BillGetListResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

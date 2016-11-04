package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.BillDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.BillGetListResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by AMoreira on 7/14/16.
 */
public class BillDetailsRequest extends BaseRequest<BillDetailsResponse> {

    public BillDetailsRequest (String url, Response.Listener<BillDetailsResponse>listener, Response.ErrorListener errorListener){
        super(Method.GET, url,null, listener, errorListener);
    }

    @Override
    protected Response<BillDetailsResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
           BillDetailsResponse response = gson.fromJson(json,BillDetailsResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

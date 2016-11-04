package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Responses.CheckListGetListResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 9/30/16.
 */
public class CheckListGetListRequest extends BaseRequest<CheckListGetListResponse> {

    public CheckListGetListRequest(String url, Response.Listener<CheckListGetListResponse> listener, Response.ErrorListener errorListener){
        super(Method.GET,url,null,listener,errorListener);
    }

    @Override
    protected Response<CheckListGetListResponse> parseNetworkResponse(NetworkResponse networkResponse) {

        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
           CheckListGetListResponse response = gson.fromJson(json,CheckListGetListResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

/**
 * Created by AMoreira on 9/30/16.
 */
public class CheckListCreateItemRequest extends BaseRequest<BaseResponse> {

    public CheckListCreateItemRequest(String url, JSONObject object, Response.Listener<BaseResponse> listener, Response.ErrorListener errorListener){
        super(Method.POST,url,object.toString(),listener,errorListener);

    }
    @Override
    protected Response<BaseResponse> parseNetworkResponse(NetworkResponse networkResponse) {
        try{
            String json = new String(networkResponse.data);
            Gson gson = new GsonBuilder().create();
            BaseResponse response = gson.fromJson(json,BaseResponse.class);
            return Response.success(response,null);
        } catch (UnsupportedOperationException uue) {
            return Response.error(new ParseError(uue));
        }
    }
}

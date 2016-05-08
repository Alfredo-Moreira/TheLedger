package com.apolloapps.theledger.DataManager.Requests;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonRequest;
import com.apolloapps.theledger.Common.NetworkConstants;

/**
 * Created by AMoreira on 5/7/16.
 */
public abstract class BaseRequest<T> extends JsonRequest<T> {

    public BaseRequest(int method, String url, String requestBody, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, requestBody, listener, errorListener);

        setRetryPolicy( new DefaultRetryPolicy(
                NetworkConstants.DEFAULT_TIMEOUT_MS,
                NetworkConstants.MAX_NUM_RETRIES,
                NetworkConstants.DEFAULT_BACKOFF_MULT));
    }


}

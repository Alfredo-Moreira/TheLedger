package com.apolloapps.theledger.DataManager.Utilities;

/**
 * Created by AMoreira on 5/7/16.
 */
public interface ServiceCallback<T> {

    public void onSuccess(T response);

    public void onError(NetworkError error);

    public void onPreExecute();

}

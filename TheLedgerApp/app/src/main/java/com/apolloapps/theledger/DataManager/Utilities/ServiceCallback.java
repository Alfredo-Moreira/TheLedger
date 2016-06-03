package com.apolloapps.theledger.DataManager.Utilities;

/**
 * Created by AMoreira on 5/7/16.
 */
public interface ServiceCallback<T> {

    void onSuccess(T response);

    void onError(NetworkError error);

    void onPreExecute();

}

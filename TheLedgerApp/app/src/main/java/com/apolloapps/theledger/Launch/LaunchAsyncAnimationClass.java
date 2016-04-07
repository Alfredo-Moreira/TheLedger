package com.apolloapps.theledger.Launch;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by AMoreira on 4/6/16.
 */
public class LaunchAsyncAnimationClass extends AsyncTask {

    private ProgressBar mProgressBar;


    public LaunchAsyncAnimationClass(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }


    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }
}

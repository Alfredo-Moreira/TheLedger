package com.apolloapps.theledger.DataManager.Utilities;

import com.apolloapps.theledger.Common.NetworkConstants;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by AMoreira on 5/7/16.
 */
public class UrlConstructor {

    private static String mBaseURL;
    private static StringBuffer mStringBuffer;

    public UrlConstructor() {
        mBaseURL = NetworkConstants.BASE_URL;
    }


    public String getFinalUrl(String endpoint) {
        return mBaseURL + endpoint;
    }

    public String getCompoundURL(LinkedHashMap<String, String> params) {
        mStringBuffer = new StringBuffer();
        mStringBuffer.append(mBaseURL);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                mStringBuffer.append(entry.getKey() + "/" + entry.getValue());
            } else {
                mStringBuffer.append(entry.getKey() + "/");
            }
        }
        mStringBuffer.deleteCharAt(mStringBuffer.lastIndexOf("/"));
        return mStringBuffer.toString();
    }

    public String addInLineParams(String url, LinkedHashMap<String, String> params) {
        mStringBuffer = new StringBuffer();
        mStringBuffer.append(url + "?");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            mStringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        mStringBuffer.deleteCharAt(mStringBuffer.lastIndexOf("&"));

        return mStringBuffer.toString();
    }
}

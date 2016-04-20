package com.apolloapps.theledger.DataManager.Models;

/**
 * Created by AMoreira on 4/13/16.
 */
public class ReferenceModel {
    private String mReferenceTitle;
    private String mReferenceLink;
    private String mReferenceDescription;

    public ReferenceModel(String title, String link, String description) {
        setReferenceTitle(title);
        setReferenceLink(link);
        setReferenceDescription(description);
    }

    public String getReferenceDescription() {
        return mReferenceDescription;
    }

    private void setReferenceDescription(String mReferenceDescription) {
        this.mReferenceDescription = mReferenceDescription;
    }

    public String getReferenceLink() {
        return mReferenceLink;
    }

    private void setReferenceLink(String mReferenceLink) {
        this.mReferenceLink = mReferenceLink;
    }

    public String getReferenceTitle() {
        return mReferenceTitle;
    }

    private void setReferenceTitle(String mReferenceTitle) {
        this.mReferenceTitle = mReferenceTitle;
    }
}

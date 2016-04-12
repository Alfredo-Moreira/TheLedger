package com.apolloapps.theledger.DataManager.Models;


/**
 * Created by AMoreira on 4/12/16.
 */
public class PersonalAccountModel {

    private String mFirstName;
    private String mLastName;
    private String mUsername;
    private String mPassword;
    private String mPhoneNumber;
    private String mEmailAddress;


    public PersonalAccountModel() {

    }

    public void setPersonalAccount(String firstName, String lastName, String username, String password, String phoneNumber, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setUsername(username);
        setPassword(password);
        setPhoneNumber(phoneNumber);
        setEmailAddress(email);
    }

    public String getEmailAddress() {
        return mEmailAddress;
    }

    private void setEmailAddress(String mEmailAddress) {
        this.mEmailAddress = mEmailAddress;
    }

    public String getLastName() {
        return mLastName;
    }

    private void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    private void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getPassword() {
        return mPassword;
    }

    private void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    private void setPhoneNumber(String mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }
}

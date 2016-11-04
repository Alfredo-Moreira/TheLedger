package com.apolloapps.theledger.DataManager;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.apolloapps.theledger.Common.AppConstants;
import com.apolloapps.theledger.Common.NetworkConstants;
import com.apolloapps.theledger.DataManager.Models.AccountModel;
import com.apolloapps.theledger.DataManager.Models.BillModel;
import com.apolloapps.theledger.DataManager.Models.CheckListModel;
import com.apolloapps.theledger.DataManager.Models.PersonalAccountModel;
import com.apolloapps.theledger.DataManager.Requests.AccountCreateRequest;
import com.apolloapps.theledger.DataManager.Requests.AccountDeleteRequest;
import com.apolloapps.theledger.DataManager.Requests.AccountGetDetailsRequest;
import com.apolloapps.theledger.DataManager.Requests.AccountGetListRequest;
import com.apolloapps.theledger.DataManager.Requests.AccountUpdateRequest;
import com.apolloapps.theledger.DataManager.Requests.BillDeleteRequest;
import com.apolloapps.theledger.DataManager.Requests.BillDetailsRequest;
import com.apolloapps.theledger.DataManager.Requests.BillGetListRequest;
import com.apolloapps.theledger.DataManager.Requests.BillUpdateRequest;
import com.apolloapps.theledger.DataManager.Requests.CheckListCheckUncheckItemRequest;
import com.apolloapps.theledger.DataManager.Requests.CheckListCreateItemRequest;
import com.apolloapps.theledger.DataManager.Requests.CheckListDeleteListRequest;
import com.apolloapps.theledger.DataManager.Requests.CheckListGetListRequest;
import com.apolloapps.theledger.DataManager.Requests.CreateBillRequest;
import com.apolloapps.theledger.DataManager.Requests.LoginRequest;
import com.apolloapps.theledger.DataManager.Requests.UserCreateAccountRequest;
import com.apolloapps.theledger.DataManager.Requests.UserGetDetailsRequest;
import com.apolloapps.theledger.DataManager.Requests.UserUpdateAccountRequest;
import com.apolloapps.theledger.DataManager.Responses.AccountGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.AccountGetListResponse;
import com.apolloapps.theledger.DataManager.Responses.BaseResponse;
import com.apolloapps.theledger.DataManager.Responses.BillDetailsResponse;
import com.apolloapps.theledger.DataManager.Responses.BillGetListResponse;
import com.apolloapps.theledger.DataManager.Responses.CheckListGetListResponse;
import com.apolloapps.theledger.DataManager.Responses.LoginResponse;
import com.apolloapps.theledger.DataManager.Responses.UserCreateAccountResponse;
import com.apolloapps.theledger.DataManager.Responses.UserGetDetailsResponse;
import com.apolloapps.theledger.DataManager.Utilities.NetworkError;
import com.apolloapps.theledger.DataManager.Utilities.ServiceCallback;
import com.apolloapps.theledger.DataManager.Utilities.UrlConstructor;
import com.apolloapps.theledger.Preferences.Preferences;
import com.apolloapps.theledger.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.LinkedHashMap;

/**
 * Created by AMoreira on 5/7/16.
 */
public class DataManager {

    private Context mContext;
    private UrlConstructor mUrlConstructor;
    private Preferences mPreferences;
    private String mUrl;
    private JSONObject mJSON;
    private RequestQueue mRequestQueue;
    private LinkedHashMap<String,String> mParams;

    public DataManager(Context context, UrlConstructor urConstructor, Preferences preferences){
        mContext =context;
        mPreferences = preferences;
        mUrlConstructor = urConstructor;
        mRequestQueue = buildRequestQueue(context);
    }

    private RequestQueue buildRequestQueue(Context context){

        CookieManager cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);
        return Volley.newRequestQueue(context);

    }


    private NetworkError createNetworkError(VolleyError error) {
        if (error != null && error.getMessage() != null) {
            try {
                JSONObject jError = new JSONObject(error.getMessage());
                com.apolloapps.theledger.DataManager.Utilities.NetworkError ne = new com.apolloapps.theledger.DataManager.Utilities.NetworkError(jError.has(mContext.getString(R.string.status)) ? jError.getInt(mContext.getString(R.string.status)) : 0,
                        jError.has(mContext.getString(R.string.code)) ? jError.getInt(mContext.getString(R.string.code)) : 0,
                        jError.has(mContext.getString(R.string.message)) ? jError.getString(mContext.getString(R.string.message)) : mContext.getString(R.string.no_message));
                return ne;
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
        if (error != null && error.networkResponse != null) {
            return new NetworkError(error.networkResponse.statusCode,
                    error.networkResponse.statusCode, error.getMessage());
        } else {
            return new NetworkError(0, 0, mContext.getResources().getString(R.string.cannot_reach_server));
        }
    }


    //The Methods
    /*
     * Login endpoint: /UserAccount/Login
     */
    public void doLogin(String username, String password, final ServiceCallback<LoginResponse> listener) {
        listener.onPreExecute();

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_USERNAME, username);
            mJSON.put(NetworkConstants.PARAM_PASSWORD, password);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.LOGIN);
        LoginRequest request = new LoginRequest(mUrl, mJSON, new Response.Listener<LoginResponse>() {
            @Override
            public void onResponse(LoginResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });
        mRequestQueue.add(request);
    }


    /*
     * Create User Account endpoint: /UserAccount/CreateAccount
     */
    public void doCreateUserAccount(PersonalAccountModel account, final ServiceCallback<UserCreateAccountResponse> listener) {
        listener.onPreExecute();


        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_FIRST_NAME, account.getFirstName());
            mJSON.put(NetworkConstants.PARAM_LAST_NAME, account.getLastName());
            mJSON.put(NetworkConstants.PARAM_USERNAME, account.getUsername());
            mJSON.put(NetworkConstants.PARAM_PASSWORD, account.getPassword());//Need SHA-1 Encryption done
            mJSON.put(NetworkConstants.PARAM_PHONE_NUMBER, account.getPhoneNumber());
            mJSON.put(NetworkConstants.PARAM_EMAIL,account.getEmailAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.USER_CREATE_ACCOUNT);
        UserCreateAccountRequest request = new UserCreateAccountRequest(mUrl, mJSON, new Response.Listener<UserCreateAccountResponse>() {

            @Override
            public void onResponse(UserCreateAccountResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Get User Details endpoint: /UserAccount/{id}
     */

    public void doGetUserDetails(int id, final ServiceCallback<UserGetDetailsResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_USER_ACCOUNT_DETAILS, String.valueOf(id));
        mUrl = mUrlConstructor.getCompoundURL(mParams);

        UserGetDetailsRequest request = new UserGetDetailsRequest(mUrl, new Response.Listener<UserGetDetailsResponse>() {
            @Override
            public void onResponse(UserGetDetailsResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    public void doUpdateUserAccount(int userId, PersonalAccountModel account, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.UPDATE_ACCOUNT, String.valueOf(userId));

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_FIRST_NAME, account.getFirstName());
            mJSON.put(NetworkConstants.PARAM_LAST_NAME, account.getLastName());
            mJSON.put(NetworkConstants.PARAM_USERNAME, account.getUsername());
            mJSON.put(NetworkConstants.PARAM_PASSWORD, account.getPassword());//Need SHA-1 Encryption done
            mJSON.put(NetworkConstants.PARAM_PHONE_NUMBER, account.getPhoneNumber());
            mJSON.put(NetworkConstants.PARAM_EMAIL, account.getEmailAddress());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        UserUpdateAccountRequest request = new UserUpdateAccountRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    public void doUpdateUserUsername() {
    }

    public void doUpdateUserPassword() {
    }

    public void doDeleteUser() {
    }

    /*
     * Get Account List  endpoint: /Account/{user_id}
     */
    public void doGetAccountList(int userId, final ServiceCallback<AccountGetListResponse> listener) {

        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_ACCOUNT_LIST, String.valueOf(userId));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        AccountGetListRequest request = new AccountGetListRequest(mUrl, new Response.Listener<AccountGetListResponse>() {
            @Override
            public void onResponse(AccountGetListResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Create Account endpoint: /Account/CreateAccount
     */

    public void doCreateAccount(int userId, AccountModel account, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_USER_ID, String.valueOf(userId));
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_TITLE, account.getAccountTitle());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_USERNAME, account.getAccountUsername());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_PASSWORD, account.getAccountPassword());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_TYPE, account.getAccountType());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_COMMENTS, account.getAccountComments());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.CREATE_ACCOUNT);

        AccountCreateRequest request = new AccountCreateRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Get Account Details endpoint: /Account/{userId}/{accountId}
     */

    public void doGetAccountDetails(int userId, int accountId, final ServiceCallback<AccountGetDetailsResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_ACCOUNT_LIST,String.valueOf(userId));
        mParams.put(String.valueOf(accountId),null);

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        AccountGetDetailsRequest request = new AccountGetDetailsRequest(mUrl, new Response.Listener<AccountGetDetailsResponse>() {
            @Override
            public void onResponse(AccountGetDetailsResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Delete Account endpoint /Account/DeleteAccount/{id}
     */

    public void doDeleteAccount(int accountId, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.DELETE_ACCOUNT, String.valueOf(accountId));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        AccountDeleteRequest request = new AccountDeleteRequest(mUrl, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);

    }

    /*
     * Update Account endpoint: Account/UpdateAccount/{id}
     */
    public void doUpdateAccount(int accountId, AccountModel account, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_TITLE, account.getAccountTitle());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_USERNAME, account.getAccountUsername());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_PASSWORD, account.getAccountPassword());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_TYPE, account.getAccountType());
            mJSON.put(NetworkConstants.PARAM_ACCOUNT_COMMENTS, account.getAccountComments());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.UPDATE_ACCOUNT, String.valueOf(accountId));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        AccountUpdateRequest request = new AccountUpdateRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);

    }


    /*
     * Get Bill List endpoint: Bill/{user_id}
     */

    public void doGetBillList(int userId, final ServiceCallback<BillGetListResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_BILLS_LIST, String.valueOf(userId));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        BillGetListRequest request = new BillGetListRequest(mUrl, new Response.Listener<BillGetListResponse>() {
            @Override
            public void onResponse(BillGetListResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     *  Post Create Bill endpoint: Bill/CreateBill
     */
    public void doCreateBill(int userId, BillModel model, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_USER_ID, String.valueOf(userId));
            mJSON.put(NetworkConstants.PARAM_BILL_NAME, model.getBillName());
            mJSON.put(NetworkConstants.PARAM_BILL_TYPE, model.getBillType());
            mJSON.put(NetworkConstants.PARAM_BILL_DUE_DATE, model.getBillDueDate());
            mJSON.put(NetworkConstants.PARAM_BILL_REMINDER_SET, model.getIsIsReminderSet());
            mJSON.put(NetworkConstants.PARAM_BILL_REMINDER_DATE, model.getmBillReminderDate());
            mJSON.put(NetworkConstants.PARAM_BILL_COMMENTS, model.getBillComments());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.CREATE_BILL);
        CreateBillRequest request = new CreateBillRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /*
     * Get Bill Details endpoint Bill/{user_id}/{id}
     */

    public void doGetBillDetails(int userId, int billId, final ServiceCallback<BillDetailsResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_BILLS_LIST, String.valueOf(userId));
        mParams.put(String.valueOf(billId), null);

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        BillDetailsRequest request = new BillDetailsRequest(mUrl, new Response.Listener<BillDetailsResponse>() {
            @Override
            public void onResponse(BillDetailsResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /**
     * Update Bill endpoint Bill/UpdateBill/{id}
     */

    public void doUpdateBill(int id, BillModel model, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.UPDATE_BILL, String.valueOf(id));

        mJSON = new JSONObject();
        try {
            mJSON.put(NetworkConstants.PARAM_BILL_NAME, model.getBillName());
            mJSON.put(NetworkConstants.PARAM_BILL_TYPE, model.getBillType());
            mJSON.put(NetworkConstants.PARAM_BILL_DUE_DATE, model.getBillDueDate());
            mJSON.put(NetworkConstants.PARAM_BILL_REMINDER_SET, model.getIsIsReminderSet());
            mJSON.put(NetworkConstants.PARAM_BILL_REMINDER_DATE, model.getmBillReminderDate());
            mJSON.put(NetworkConstants.PARAM_BILL_COMMENTS, model.getBillComments());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mUrl = mUrlConstructor.getCompoundURL(mParams);

        BillUpdateRequest request = new BillUpdateRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /**
     * Delete Bill endpoint Bill/DeleteBill/{id}
     */

    public void doDeleteBill(int billId, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.DELETE_BILL, String.valueOf(billId));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        BillDeleteRequest request = new BillDeleteRequest(mUrl, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /**
     * Get Checklist endpoint CheckList/{user_id}/{todo_id vs completed_id}
     */
    public void doGetChecklistList(int user_id, int choice, final ServiceCallback<CheckListGetListResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.GET_CHECKLIST, String.valueOf(user_id));
        if(choice == AppConstants.TODO_CHECKLIST) {
            mParams.put("false",null);
        }else {
            mParams.put("true",null);
        }

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        CheckListGetListRequest request = new CheckListGetListRequest(mUrl, new Response.Listener<CheckListGetListResponse>() {
            @Override
            public void onResponse(CheckListGetListResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }


    /**
     * Delete All checked Items endpoint CheckList/DeleteCheckListItem/{user_id}
     */
    public void doDeleteAllCheckListCheckedItems(int user_id, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.DELETE_CHECKLIST,String.valueOf(user_id));

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        CheckListDeleteListRequest request = new CheckListDeleteListRequest(mUrl, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /**
     * Create CheckList Item endpoint CheckList/CreateCheckListItem
     */


    public void doCreateCheckListItem(int userId, String title, final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();


        mJSON = new JSONObject();

        try {
            mJSON.put(NetworkConstants.PARAM_USER_ID,String.valueOf(userId));
            mJSON.put(NetworkConstants.PARAM_CHECKLIST_ITEM_TITLE, title);
            mJSON.put(NetworkConstants.PARAM_CHECKLIST_CHECKED, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        mUrl = mUrlConstructor.getFinalUrl(NetworkConstants.CREATE_CHECKLIST_ITEM);

        CheckListCreateItemRequest request = new CheckListCreateItemRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);
    }

    /**
     * Check/Uncheck Item in CheckList endpoint CheckList/CheckItem/{id}
     */

    public void doUpdateCheckListState(int checkListId,boolean checkedState,final ServiceCallback<BaseResponse> listener) {
        listener.onPreExecute();

        mParams = new LinkedHashMap<>();
        mParams.put(NetworkConstants.UPDATE_CHECKED_STATE,String.valueOf(checkListId));

        mJSON = new JSONObject();

        try {
            mJSON.put(NetworkConstants.PARAM_CHECKLIST_CHECKED,checkedState);
        }catch (JSONException e) {
            e.printStackTrace();
        }

        mUrl = mUrlConstructor.getCompoundURL(mParams);

        CheckListCheckUncheckItemRequest request = new CheckListCheckUncheckItemRequest(mUrl, mJSON, new Response.Listener<BaseResponse>() {
            @Override
            public void onResponse(BaseResponse response) {
               listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(createNetworkError(error));
            }
        });

        mRequestQueue.add(request);

    }


}

package com.amadeussoftua.chartlist.network.responses;

import android.text.TextUtils;

/**
 * Created by Artem on 26.07.2014.
 */
public class BaseServerResponse {

    private String error;

    private String message;

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess(){
        return TextUtils.isEmpty(error);
    }

}

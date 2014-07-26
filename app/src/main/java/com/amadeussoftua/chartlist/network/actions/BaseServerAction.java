package com.amadeussoftua.chartlist.network.actions;

import com.amadeussoftua.chartlist.network.NetworkManager;
import com.amadeussoftua.chartlist.network.responses.BaseServerResponse;
import com.loopj.android.http.RequestParams;

/**
 * Created by Artem on 26.07.2014.
 */
public abstract class BaseServerAction<T extends BaseServerResponse> {

    private T response;
    private int limit;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public boolean isSuccess() {
        return response != null && response.isSuccess();
    }

    public void fillRequestParams(RequestParams params) {
        params.put("api_key", NetworkManager.LAST_FM_API_KEY);
        params.put("format", "json");
        params.put("limit", limit);
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public abstract String getAction();

    public abstract Class getResponseParseClass();


}

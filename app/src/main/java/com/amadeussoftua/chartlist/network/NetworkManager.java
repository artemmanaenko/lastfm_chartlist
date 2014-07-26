package com.amadeussoftua.chartlist.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.amadeussoftua.chartlist.Debug;
import com.amadeussoftua.chartlist.network.actions.BaseServerAction;
import com.amadeussoftua.chartlist.network.actions.CountryChartAction;
import com.amadeussoftua.chartlist.network.actions.WorldChartAction;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

/**
 * Created by Artem on 26.07.2014.
 */
public class NetworkManager {

    public static final String LAST_FM_API_KEY = "e5896871496e3a6beec1caf18d52f1a3";
    public static final String SERVER_URL = "http://ws.audioscrobbler.com/2.0/";

    private final int REQUEST_TIMEOUT_DEFAULT_MS = 40 * 1000;
    private final int PAGE_LIMIT = 10;

    private static final String TAG = "NetworkManager";

    private static NetworkManager instance;

    public static synchronized NetworkManager getInstance(Context context) {
        if (instance == null) {
            synchronized (NetworkManager.class) {
                if (instance == null) {
                    instance = new NetworkManager(context);
                }
            }
        }
        return instance;
    }

    private AsyncHttpClient httpClient;
    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
        httpClient = new AsyncHttpClient();
        httpClient.setTimeout(REQUEST_TIMEOUT_DEFAULT_MS);
    }

    public void requestChartListByCountry(String country) {
        CountryChartAction action = new CountryChartAction();
        action.setLimit(PAGE_LIMIT);
        action.setCountry(country);
        executeAction(action, new ServerResponseHandler(action));
    }

    public void requestWorldChartList() {
        WorldChartAction action = new WorldChartAction();
        action.setLimit(PAGE_LIMIT);
        executeAction(action, new ServerResponseHandler(action));
    }

    private void executeAction(BaseServerAction serverAction, ServerResponseHandler responseHandler) {
        String url = SERVER_URL + serverAction.getAction();
        RequestParams params = new RequestParams();
        serverAction.fillRequestParams(params);
        httpClient.get(context, url, params, responseHandler);
        printRequest(url, params);
    }

    private void printRequest(String url, RequestParams params) {
        StringBuilder builder = new StringBuilder();
        builder.append("Request started [ " + url + " ]");
        builder.append("\n~~~~~~> Params: ").append(String.valueOf(params));
        Debug.logD(TAG, builder.toString());
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnected();
        return isConnected;
    }

}

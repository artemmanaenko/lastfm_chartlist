package com.amadeussoftua.chartlist.network;

import com.amadeussoftua.chartlist.Debug;
import com.amadeussoftua.chartlist.events.ServerProblemEvent;
import com.amadeussoftua.chartlist.network.actions.BaseServerAction;
import com.amadeussoftua.chartlist.network.responses.BaseServerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import de.greenrobot.event.EventBus;

/**
 * Created by Artem on 26.07.2014.
 */
public class ServerResponseHandler extends JsonHttpResponseHandler {

    private final String TAG = "ServerResponseHandler";

    private static Gson gson;
    private BaseServerAction serverAction;

    public ServerResponseHandler(BaseServerAction serverAction) {
        super(DEFAULT_CHARSET);
        this.serverAction = serverAction;
    }

    private Gson getGson() {
        if (gson == null) {
            synchronized (Gson.class) {
                if (gson == null) {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gson = gsonBuilder.create();
                }
            }
        }
        return gson;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
        onSuccess(statusCode, headers, response.toString());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
        EventBus.getDefault().post(new ServerProblemEvent());
    }

    @Override
    public void onSuccess(final int statusCode, final Header[] headers, final String responseBody) {
        String url = NetworkManager.SERVER_URL + serverAction.getAction();
        Debug.logD(TAG, "response for " + url + ": " + responseBody + "\nstatusCode: " + statusCode);

        if (statusCode != HttpStatus.SC_NO_CONTENT) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String responseString = responseBody.replaceAll(":\\[\\]", ":null");
                        BaseServerResponse response = parseResponse(responseString);
                        serverAction.setResponse(response);
                        postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(serverAction);
                            }
                        });
                    } catch (final Exception e) {
                        postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                EventBus.getDefault().post(serverAction);
                            }
                        });
                    }
                }
            }).start();
        }
    }

    protected BaseServerResponse parseResponse(String responseBody) {
        Class clazz = serverAction.getResponseParseClass();
        BaseServerResponse serverResponse = (BaseServerResponse) getGson().fromJson(responseBody, clazz);
        return serverResponse;
    }
}

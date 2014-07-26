package com.amadeussoftua.chartlist.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.amadeussoftua.chartlist.events.ConnectionLostEvent;
import com.amadeussoftua.chartlist.events.NetworkConnectedEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by Artem on 26.07.2014.
 */
public class ConnectionReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = info != null && info.isConnected();
        if (isConnected) {
            EventBus.getDefault().post(new NetworkConnectedEvent());
        } else {
            EventBus.getDefault().post(new ConnectionLostEvent());
        }

    }

}

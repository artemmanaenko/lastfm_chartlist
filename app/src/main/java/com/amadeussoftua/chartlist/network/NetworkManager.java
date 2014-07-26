package com.amadeussoftua.chartlist.network;

/**
 * Created by Artem on 26.07.2014.
 */
public class NetworkManager {

    private static NetworkManager instance;

    public static synchronized NetworkManager getInstance(){
        if(instance == null){
            instance = new NetworkManager();
        }
        return instance;
    }

}

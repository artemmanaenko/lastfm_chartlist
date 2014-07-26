package com.amadeussoftua.chartlist.ui;

import android.support.v4.app.ListFragment;

import com.amadeussoftua.chartlist.network.NetworkManager;

/**
 * Created by Artem on 26.07.2014.
 */
public class ChartListFragment extends ListFragment {

    @Override
    public void onResume() {
        super.onResume();
        NetworkManager.getInstance(getActivity()).requestWorldChartList();
    }
}

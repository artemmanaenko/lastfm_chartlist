package com.amadeussoftua.chartlist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.amadeussoftua.chartlist.R;
import com.amadeussoftua.chartlist.events.ConnectionLostEvent;
import com.amadeussoftua.chartlist.events.NetworkConnectedEvent;
import com.amadeussoftua.chartlist.events.ServerProblemEvent;
import com.amadeussoftua.chartlist.network.NetworkManager;
import com.amadeussoftua.chartlist.network.actions.CountryChartAction;
import com.amadeussoftua.chartlist.network.actions.WorldChartAction;
import com.amadeussoftua.chartlist.network.data.Artist;
import com.amadeussoftua.chartlist.network.responses.CountryChartResponse;
import com.amadeussoftua.chartlist.network.responses.WorldChartResponse;
import com.amadeussoftua.chartlist.ui.adapters.ChartListAdapter;

import de.greenrobot.event.EventBus;

/**
 * Created by Artem on 26.07.2014.
 */
public class ChartListFragment extends ListFragment {

    public static final String EXTRAS_KEY_COUNTRY = "key_country";

    private ChartListAdapter adapter;
    private TextView stateTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart_list, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void onEvent(CountryChartAction action) {
        if (action.isSuccess()) {
            CountryChartResponse response = (CountryChartResponse) action.getResponse();
            adapter.setArtistList(response.getArtists().getArtists());
        } else
            processServerError(action.getResponse().getMessage());
    }

    public void onEvent(WorldChartAction action) {
        if (action.isSuccess()) {
            WorldChartResponse response = (WorldChartResponse) action.getResponse();
            adapter.setArtistList(response.getArtists().getArtists());
        } else
            processServerError(action.getResponse().getMessage());
    }

    public void onEvent(ServerProblemEvent action) {
        processServerError(getString(R.string.server_problem));
    }


    public void onEvent(ConnectionLostEvent action) {
        processServerError(getString(R.string.no_internet_connection));
    }

    public void onEvent(NetworkConnectedEvent action) {
        loadData();
    }

    private void processServerError(String message) {
        stateTextView.setText(message);
        adapter.clearItems();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initEmptyView();
        initAdapter();
        loadData();
    }

    private void loadData() {
        if(NetworkManager.getInstance(getActivity()).isNetworkAvailable()){
            setLoadingState();
            Bundle arguments = getArguments();
            if (arguments != null) {
                String country = arguments.getString(EXTRAS_KEY_COUNTRY);
                if ("World".equals(country))
                    NetworkManager.getInstance(getActivity()).requestWorldChartList();
                else
                    NetworkManager.getInstance(getActivity()).requestChartListByCountry(country);
            }
        } else {
            processServerError(getString(R.string.no_internet_connection));
        }
    }

    private void initEmptyView() {
        stateTextView = (TextView) getView().findViewById(android.R.id.empty);
    }

    private void setLoadingState() {
        stateTextView.setText(getString(R.string.loading));
    }

    private void initAdapter() {
        adapter = new ChartListAdapter(getActivity());
        getListView().setAdapter(adapter);
        getListView().setOnItemClickListener(itemClickListener);
    }

    private AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            Artist artist = (Artist) adapter.getItem(position);
            String url = artist.getUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        }
    };

}

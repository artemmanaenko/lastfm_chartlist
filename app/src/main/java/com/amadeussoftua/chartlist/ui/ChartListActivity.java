package com.amadeussoftua.chartlist.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.amadeussoftua.chartlist.R;


public class ChartListActivity extends ActionBarActivity {

    private String[] countriesList = new String[]{"World", "Ukraine", "Denmark"};
    private Spinner countrySelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_list);
        initSpinner();
    }

    private void initSpinner() {
        countrySelector = (Spinner) findViewById(R.id.countrySelector);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, countriesList);
        countrySelector.setAdapter(adapter);
        countrySelector.setOnItemSelectedListener(countrySelectedListener);
        countrySelector.setSelection(0);
    }

    private AdapterView.OnItemSelectedListener countrySelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
            String country = countriesList[position];
            showChartListFragment(country);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    private void showChartListFragment(String country){
        Bundle arguments = new Bundle();
        arguments.putString(ChartListFragment.EXTRAS_KEY_COUNTRY, country);
        ChartListFragment fragment = new ChartListFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

}

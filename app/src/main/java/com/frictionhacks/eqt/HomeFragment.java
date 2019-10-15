package com.frictionhacks.eqt;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private List<StockDataModel> bseList = new ArrayList<>();
    private RecyclerView bseRecyclerView;
    private StockAdapter bseAdapter;
    private Button btnStockSearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        bseRecyclerView = view.findViewById(R.id.rv_main_bse);
        bseAdapter = new StockAdapter(bseList);
        RecyclerView.LayoutManager bseLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        bseRecyclerView.setLayoutManager(bseLayoutManager);
        bseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bseRecyclerView.setAdapter(bseAdapter);

        prepareFakeData();


        ValueLineChart mCubicValueLineChart = view.findViewById(R.id.lc_main);

        mCubicValueLineChart.clearChart();

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(Color.parseColor("#1BCCB0"));


        series.addPoint(new ValueLinePoint("Seen", .6f));

        for (int i = 0; i <= 17; i++) {
            final int random = new Random().nextInt(16) + 1;
            series.addPoint(new ValueLinePoint(String.valueOf(i + 1), random));
        }

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();

        return view;
    }

    private void prepareFakeData() {

        StockDataModel bseStock = new StockDataModel("BSE", "Prixe", "bse");
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);

        bseAdapter.notifyDataSetChanged();


    }
}

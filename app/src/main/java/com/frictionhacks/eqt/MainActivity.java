package com.frictionhacks.eqt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<StockDataModel> nseList= new ArrayList<>();
    private List<StockDataModel> bseList= new ArrayList<>();
    private RecyclerView nseRecyclerView,bseRecyclerView;
    private StockAdapter bseAdapter,nseAdapter;
    private Button btnStockSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bseRecyclerView=findViewById(R.id.rv_main_bse);
        nseRecyclerView=findViewById(R.id.rv_main_nse);

        bseAdapter=new StockAdapter(bseList);
        nseAdapter=new StockAdapter(nseList);

        RecyclerView.LayoutManager bseLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager nseLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);

        bseRecyclerView.setLayoutManager(bseLayoutManager);
        nseRecyclerView.setLayoutManager(nseLayoutManager);

        bseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        nseRecyclerView.setItemAnimator(new DefaultItemAnimator());

        nseRecyclerView.setAdapter(nseAdapter);
        bseRecyclerView.setAdapter(bseAdapter);
        
        prepareFakeData();
        btnStockSearch=findViewById(R.id.btn_main_find);

        btnStockSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,StockSearchActivity.class);
                startActivity(intent);
            }
        });

        ValueLineChart mCubicValueLineChart = findViewById(R.id.lc_main);

        mCubicValueLineChart.clearChart();

        ValueLineSeries series = new ValueLineSeries();
        series.setColor(Color.parseColor("#1BCCB0"));


        series.addPoint(new ValueLinePoint("Seen",.6f));

        for(int i=0;i<=17;i++){
            final int random = new Random().nextInt(16) + 1;
            series.addPoint(new ValueLinePoint(String.valueOf(i+1),random));
        }

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.startAnimation();
    }

    private void prepareFakeData() {

        StockDataModel bseStock= new StockDataModel("BSE","Prixe","bse");
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);

        bseAdapter.notifyDataSetChanged();

        StockDataModel nseStock= new StockDataModel("NSE","Prixe_ns","S_sts");
        nseList.add(nseStock);
        nseList.add(nseStock);
        nseList.add(nseStock);
        nseList.add(nseStock);
        nseList.add(nseStock);
        nseList.add(nseStock);

        nseAdapter.notifyDataSetChanged();


    }
}

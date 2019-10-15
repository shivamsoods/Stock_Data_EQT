package com.frictionhacks.eqt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private List<StockDataModel> bseList = new ArrayList<>();
    private RecyclerView bseRecyclerView;
    private StockAdapter bseAdapter;
    private Button btnStockSearch;
    private ChipNavigationBar nbBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bseRecyclerView = findViewById(R.id.rv_main_bse);
        bseAdapter = new StockAdapter(bseList);
        RecyclerView.LayoutManager bseLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        bseRecyclerView.setLayoutManager(bseLayoutManager);
        bseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bseRecyclerView.setAdapter(bseAdapter);


        nbBottom = findViewById(R.id.nv_main_bottom);
        nbBottom.setItemEnabled(2131165311,true);
        nbBottom.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Log.d("TAGg", "onItemSelected: "+i);
                switch (i) {
                    case 2131165311:
                        //home
                        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent1);
                        break;
                    case 2131165310:
                        //equity
                        Intent intent2=new Intent(getApplicationContext(),StockSearchActivity.class);
                        startActivity(intent2);
                        break;
                    case 2131165313:
                        //search
                        Intent intent3=new Intent(getApplicationContext(),StockSearchActivity.class);
                        startActivity(intent3);

                        break;
                    case 2131165312:
                        //ocr
                        Intent intent4=new Intent(getApplicationContext(),StockSearchActivity.class);
                        startActivity(intent4);

                        break;

                    default:
                        Intent intent5=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent5);
                        break;
                }
            }
        });
        prepareFakeData();
        btnStockSearch = findViewById(R.id.btn_main_find);
        btnStockSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StockSearchActivity.class);
                startActivity(intent);
            }
        });

        ValueLineChart mCubicValueLineChart = findViewById(R.id.lc_main);

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

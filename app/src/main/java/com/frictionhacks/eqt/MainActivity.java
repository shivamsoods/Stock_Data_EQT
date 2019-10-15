package com.frictionhacks.eqt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

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

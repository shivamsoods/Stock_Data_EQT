package com.frictionhacks.eqt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<StockDataModel> nseList= new ArrayList<>();
    private List<StockDataModel> bseList= new ArrayList<>();
    private RecyclerView nseRecyclerView,bseRecyclerView;
    private StockAdapter bseAdapter,nseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bseRecyclerView=findViewById(R.id.rv_main_bse);
        nseRecyclerView=findViewById(R.id.rv_main_nse);

        bseAdapter=new StockAdapter(bseList);
        nseAdapter=new StockAdapter(nseList);

        nseRecyclerView.setAdapter(nseAdapter);
        bseRecyclerView.setAdapter(bseAdapter);
        
        prepareFakeData();
    }

    private void prepareFakeData() {

        StockDataModel bseStock= new StockDataModel("BSE","Prixe","bse");
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);
        bseList.add(bseStock);

        StockDataModel nseStock= new StockDataModel("NSE","Prixe_ns","S_sts");


    }
}

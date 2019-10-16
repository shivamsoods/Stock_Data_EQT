package com.frictionhacks.eqt;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hussain_chachuliya.customsearch.SearchAdapterHolder;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockDetailFragment extends Fragment {
    private Bundle bun;
    private TextView tvStockName,tvDayHigh,tvDayLow,tvDayOpen,tvLastTradedPrice;

    public StockDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_stock_detail, container, false);

        bun=getArguments();
        assert bun != null;
        String name= bun.getString("name");
        String dayOpen= bun.getString("do");
        String dh= bun.getString("dh");
        String dl= bun.getString("dl");
        String ltp= bun.getString("ltp");



        Log.d(TAG, "onCreateView: "+ name);
        Toast.makeText(getContext(), "name is "+name, Toast.LENGTH_SHORT).show();

        tvStockName=view.findViewById(R.id.tv_detail_stock_name);
        tvDayHigh=view.findViewById(R.id.tv_detail_dh_value);
        tvDayLow=view.findViewById(R.id.tv_detail_dl_value);
        tvDayOpen=view.findViewById(R.id.tv_detail_do_value);
        tvLastTradedPrice=view.findViewById(R.id.tv_detail_ltp_value);

        tvStockName.setText(name);
        tvDayHigh.setText(dh);
        tvDayLow.setText(dl);
        tvLastTradedPrice.setText(ltp);
        tvDayOpen.setText(dayOpen);

        return view;
    }

}

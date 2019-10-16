package com.frictionhacks.eqt;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockDetailFragment extends Fragment {
private Bundle bun;

    public StockDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_stock_detail, container, false);

        bun=getArguments();
        String name= bun.getString("name");

        Log.d(TAG, "onCreateView: "+ name);
        Toast.makeText(getContext(), "name is "+name, Toast.LENGTH_SHORT).show();
        return view;
    }

}

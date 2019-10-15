package com.frictionhacks.eqt;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ramotion.fluidslider.FluidSlider;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquityFragment extends Fragment {


    public EquityFragment() {
        // Required empty public constructor
    }
    private Button btnSearchSubmit;
    private RecyclerView searchRecyclerView;
    private List<StockDataModel> searchResultList= new ArrayList<>();
    private StockAdapter searchResultAdapter;
    private LinearLayout llSearchTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_equity, container, false);

        btnSearchSubmit=view.findViewById(R.id.btn_search_submit);
        searchRecyclerView=view.findViewById(R.id.rv_search_stock);
        llSearchTop=view.findViewById(R.id.ll_search_top);

        searchResultAdapter=new StockAdapter(searchResultList);
        RecyclerView.LayoutManager searchLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        searchRecyclerView.setLayoutManager(searchLayoutManager);
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchRecyclerView.setAdapter(searchResultAdapter);


        StockDataModel bseStock= new StockDataModel("RESS","Prizze","jaj");
        searchResultList.add(bseStock);
        searchResultList.add(bseStock);
        searchResultList.add(bseStock);
        searchResultList.add(bseStock);
        searchResultList.add(bseStock);
        searchResultList.add(bseStock);
        searchResultAdapter.notifyDataSetChanged();

        searchRecyclerView.setVisibility(View.GONE);
        final String min = "Short time";
        final String max = "Long time";


        final FluidSlider slider = view.findViewById(R.id.fs_search);
        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //textView.setVisibility(View.INVISIBLE);
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                //textView.setVisibility(View.VISIBLE);
                return Unit.INSTANCE;
            }
        });


        slider.setPosition(0.3f);
        slider.setStartText(min);
        slider.setEndText(max);



        btnSearchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pos=slider.getPosition();
                pos=pos*100;
                Log.d("TAAG", "value set is "+pos );
                llSearchTop.setVisibility(View.GONE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    return view;
    }



}

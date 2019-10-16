package com.frictionhacks.eqt;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.hussain_chachuliya.customsearch.CustomSearch;
import com.hussain_chachuliya.customsearch.SearchAdapterHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockSearchFragment extends Fragment {
    private SearchAdapterHolder holder;
    private final int REQ_CODE = 55;


    public StockSearchFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_stock_search, container, false);


        holder = new SearchAdapterHolder();
        holder.addAdapter(getListOfStrings(), REQ_CODE);

        CustomSearch.start(StockSearchFragment.this, REQ_CODE, holder);


        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE && resultCode == RESULT_OK) {
            Toast.makeText(getContext(),"clicked item "+data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT), Toast.LENGTH_SHORT).show();
            Bundle bun=new Bundle();
            bun.putString("name",data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));


            loadFragment(new StockDetailFragment(),bun);
        }
    }

    @NonNull
    private List<String> getListOfStrings() {
        List<String> data = new ArrayList<>();
        data.add("HDFBAN");
        data.add("RELPET");
        data.add("HDFC");
        data.add("INFTEC");
        data.add("ICIBAN");
        data.add("ITC");
        data.add("TCS");
        data.add("KOTMAH");
        data.add("LARTOU");
        data.add("HINLEV");
        data.add("AXIBAN");
        data.add("STABAN");
        data.add("BAJFI");
        data.add("MARUTI");
        data.add("INDBA");
        data.add("ASIPAI");
        data.add("BHAAIR");
        data.add("HCLTEC");
        data.add("TITIND");
        data.add("MAHMAH");
        data.add("BAFINS");
        data.add("NTPC");
        data.add("NESIND");
        data.add("POWGRI");
        data.add("ULTCEM");
        data.add("TECMAH");
        data.add("SUNPHA");
        data.add("ONGC");
        data.add("BAAUTO");
        data.add("BHARAS");
        data.add("INDOIL");
        data.add("COALIN");
        data.add("WIPRO");
        data.add("HERHON");
        data.add("BRIIND");
        data.add("UNIP");
        data.add("DRREDD");
        data.add("ADAPOR");
        data.add("GRASIM");
        data.add("VEDLIM");
        data.add("HINDAL");
        data.add("TATSTE");
        data.add("EICMOT");
        data.add("GAIL");
        data.add("JSWSTE");
        data.add("BHAINF");
        data.add("CIPLA");
        data.add("TATMOT");
        data.add("ZEEENT");
        data.add("YESBAN");

        return data;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        holder.removeAdapter(REQ_CODE);
    }



    private void loadFragment(Fragment fragment, Bundle bun) {
        AppCompatActivity activity= (AppCompatActivity) getActivity();
        fragment.setArguments(bun);
        FragmentTransaction ft=activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, fragment);
        ft.commit();
    }


    private void loadFragment(Fragment fragment) {
        AppCompatActivity activity= (AppCompatActivity) getActivity();
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, fragment);
        ft.commit();
    }
}

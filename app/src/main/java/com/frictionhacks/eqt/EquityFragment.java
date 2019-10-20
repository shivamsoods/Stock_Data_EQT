package com.frictionhacks.eqt;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ramotion.fluidslider.FluidSlider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class EquityFragment extends Fragment {


    public EquityFragment() {
        // Required empty public constructor
    }

    private Button btnSearchSubmit;
    private RecyclerView searchRecyclerView;
    private List<StockDataModel> searchResultList = new ArrayList<>();
    private EquitySearchAdapter searchResultAdapter;
    private RequestQueue queue;
    private LinearLayout llEquityStonk;
    private String url_search;
    final String min = "Short time";
    final String max = "Long time";
    private EditText etBudget;
    private ProgressBar pbEquity;
    private CardView cvEquityAll;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_equity, container, false);

        btnSearchSubmit = view.findViewById(R.id.btn_search_submit);
        searchRecyclerView = view.findViewById(R.id.rv_equity_stock);
        cvEquityAll=view.findViewById(R.id.cv_equity_all);

        etBudget=view.findViewById(R.id.et_search_budget);
        searchResultAdapter = new EquitySearchAdapter(searchResultList);

        pbEquity=view.findViewById(R.id.pb_equity);
        llEquityStonk=view.findViewById(R.id.ll_equity_stonk);

        RecyclerView.LayoutManager searchLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        searchRecyclerView.setLayoutManager(searchLayoutManager);
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchRecyclerView.setAdapter(searchResultAdapter);


        searchResultList.clear();
        queue = Volley.newRequestQueue(getContext());

        pbEquity.setVisibility(View.GONE);
        llEquityStonk.setVisibility(View.GONE);

        final FluidSlider slider = view.findViewById(R.id.fs_search);
        slider.setBeginTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return Unit.INSTANCE;
            }
        });

        slider.setEndTrackingListener(new Function0<Unit>() {
            @Override
            public Unit invoke() {
                return Unit.INSTANCE;
            }
        });

        slider.setBubbleText("Days");
        //slider.setColorBubble(Color.parseColor("#13E42D"));
        slider.setColorBar(Color.parseColor("#13EDCA"));
        slider.setPosition(0.5f);
        slider.setStartText(min);
        slider.setEndText(max);


        btnSearchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float pos = slider.getPosition();
                pos=pos*100;
                int posi;
                String budget=etBudget.getText().toString();

                cvEquityAll.setVisibility(View.GONE);
                pbEquity.setVisibility(View.VISIBLE);
                searchRecyclerView.setVisibility(View.GONE);

                if(budget.isEmpty()){
                    budget="0";
                    Toast.makeText(getContext(), "Please enter a Budget", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(),MainActivity.class));
                }

                Log.d(TAG, "int value budget "+budget);

                if(pos>40){
                    posi=1;
                }
                else {
                    posi=0;
                }
               onlineSearchRequest(budget,String.valueOf(posi));

            }
        });
        return view;

    }


private void onlineSearchRequest(String budget,String tenure){

        url_search=getString(R.string.url_base)+"budget?budget="+budget+"&ls="+tenure;
    Log.d(TAG, "budget url "+url_search);

    //https://f19773f6.ngrok.io/budget?budget=30000&ls=0

    JsonArrayRequest req = new JsonArrayRequest(url_search,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d(TAG, "budget response "+response);
                    jsonSearchParseSet(response);
                }

            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d(TAG, "error " + error);
            llEquityStonk.setVisibility(View.VISIBLE);
            cvEquityAll.setVisibility(View.GONE);
            searchRecyclerView.setVisibility(View.GONE);


        }
    });

    queue.add(req);
}

private void jsonSearchParseSet(JSONArray jsonText){

    try {

        for (int j = 0; j < jsonText.length(); j++) {
            JSONObject obj = jsonText.getJSONObject(j);
            JSONArray gArray = obj.getJSONArray("graph_values");
            JSONArray tArray = obj.getJSONArray("time_values");

            ArrayList<String> gVal = new ArrayList<>();
            for (int h = 0; h < gArray.length(); h++) {
                gVal.add(gArray.getString(h));
            }

            ArrayList<String> tVal = new ArrayList<>();
            for (int h = 0; h < gArray.length(); h++) {
                tVal.add(tArray.getString(h));
            }
            //0=home 1=equity
            searchResultList.add(new StockDataModel(obj.getString("NAME"), obj.getString("DO"), obj.getString("DH"), obj.getString("DL"), obj.getString("LTP"), obj.getString("PDC"), gVal, tVal,"1"));
            searchResultAdapter.notifyDataSetChanged();
            pbEquity.setVisibility(View.GONE);
            searchRecyclerView.setVisibility(View.VISIBLE);
        }


    } catch (JSONException e) {
        e.printStackTrace();
    }


}


}

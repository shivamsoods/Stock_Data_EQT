package com.frictionhacks.eqt;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockDetailFragment extends Fragment {
    private Bundle bun;
    private TextView tvStockName, tvDayHigh, tvDayLow, tvDayOpen, tvLastTradedPrice;
    private ValueLineSeries series;
    private RequestQueue queue;
    private ValueLineChart mCubicValueLineChart;
    private ArrayList<String> gVal, tVal;

    public StockDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stock_detail, container, false);

        bun = getArguments();
        assert bun != null;
        String name = bun.getString("name");
        String dayOpen = bun.getString("do");
        String dh = bun.getString("dh");
        String dl = bun.getString("dl");
        String ltp = bun.getString("ltp");
        String userRec = bun.getString("userRec");
        gVal = (ArrayList<String>) getArguments().getSerializable("gVal");
        tVal = (ArrayList<String>) getArguments().getSerializable("tVal");

        queue = Volley.newRequestQueue(getContext());

        mCubicValueLineChart = view.findViewById(R.id.lc_detail);
        mCubicValueLineChart.clearChart();

        series = new ValueLineSeries();
        series.setColor(Color.parseColor("#1BCCB0"));

        prepareGraph();

        //Log.d(TAG, "onCreateView: "+ name);
        // Toast.makeText(getContext(), "name is "+name, Toast.LENGTH_SHORT).show();

        tvStockName = view.findViewById(R.id.tv_detail_stock_name);
        tvDayHigh = view.findViewById(R.id.tv_detail_dh_value);
        tvDayLow = view.findViewById(R.id.tv_detail_dl_value);
        tvDayOpen = view.findViewById(R.id.tv_detail_do_value);
        tvLastTradedPrice = view.findViewById(R.id.tv_detail_ltp_value);

        tvStockName.setText(name);
        tvDayHigh.setText(dh);
        tvDayLow.setText(dl);
        tvLastTradedPrice.setText(ltp);
        tvDayOpen.setText(dayOpen);


        if (userRec.equals("1")) {
            sendClickedStock(name);
        }

        return view;
    }

    private void prepareGraph() {

        for (int i = 0; i < gVal.size(); i++) {

            series.addPoint(new ValueLinePoint(tVal.get(i), Float.parseFloat(gVal.get(i))));

        }

        mCubicValueLineChart.addSeries(series);
        mCubicValueLineChart.setUseDynamicScaling(true);
        mCubicValueLineChart.startAnimation();

    }

    private void sendClickedStock(String s_code) {

        String url_click = getString(R.string.url_base) + "click?click=" + s_code;
        //Log.d(VolleyLog.TAG, "budget url "+url_click);

        //https://f19773f6.ngrok.io/budget?budget=30000&ls=0

        JsonArrayRequest req = new JsonArrayRequest(url_click,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(VolleyLog.TAG, "budget response " + response);
                        //jsonSearchParseSet(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(VolleyLog.TAG, "error " + error);


            }
        });

        queue.add(req);
    }


}

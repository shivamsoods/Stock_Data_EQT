package com.frictionhacks.eqt;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLinePoint;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private List<StockDataModel> bseList = new ArrayList<>();
    private RecyclerView bseRecyclerView;
    private StockAdapter bseAdapter;
    private Button btnStockSearch;
    private StockDetailAdapter stockDetailAdapter;
    private RequestQueue queue;
    private String url_sensex = "https://92878288.ngrok.io/sensex";
    private String url_top5="https://92878288.ngrok.io/top5";
    private TextView tvDH, tvDL, tvDO, tvLTP, tvName;
    private ValueLineSeries series;
    private ValueLineChart mCubicValueLineChart;
    private LinearLayout llAllMain;
    private ProgressBar pbMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        llAllMain=view.findViewById(R.id.ll_all_main);
        pbMain=view.findViewById(R.id.pb_main);
        llAllMain.setVisibility(View.GONE);
        pbMain.setVisibility(View.VISIBLE);

        bseRecyclerView = view.findViewById(R.id.rv_main_bse);
        bseAdapter = new StockAdapter(bseList);
        RecyclerView.LayoutManager bseLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        bseRecyclerView.setLayoutManager(bseLayoutManager);
        bseRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bseRecyclerView.setAdapter(bseAdapter);


        bseList.clear();

        tvDH = view.findViewById(R.id.tv_main_dh_value);
        tvDL = view.findViewById(R.id.tv_main_dl_value);
        tvDO = view.findViewById(R.id.tv_main_do_value);
        tvLTP = view.findViewById(R.id.tv_main_ltp_value);
        tvName = view.findViewById(R.id.tv_main_stock_name);

        queue = Volley.newRequestQueue(getContext());
        onlineRealData();
        onlineBseData();
        //prepareFakeData();

        mCubicValueLineChart = view.findViewById(R.id.lc_main);
        mCubicValueLineChart.clearChart();
        series = new ValueLineSeries();

        series.setColor(Color.parseColor("#1BCCB0"));
//        for (int i = 0; i <= 5; i++) {
//            final float random = new Random().nextFloat()*38416.2f+ 1;
//            series.addPoint(new ValueLinePoint(String.valueOf(i + 1), random));
//        }


        return view;
    }


    private void onlineBseData(){
        JsonArrayRequest req= new JsonArrayRequest(url_top5,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("VOLL", "top5 " + response);
                        jsonBseParseSet(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error " + error);

            }
        });

        queue.add(req);
    }
    private void jsonBseParseSet(JSONArray jsonText) {

        try {

            for(int j=0;j<jsonText.length();j++){
                JSONObject obj= jsonText.getJSONObject(j);
                JSONArray gArray=obj.getJSONArray("graph_values");
                //Log.d(TAG, "g1    "+gArray.getString(0));

               ArrayList<String> gVal=new ArrayList<>();
               for (int h= 0 ;h<gArray.length();h++){
                   gVal.add(gArray.getString(h));
               }
                bseList.add(new StockDataModel(obj.getString("NAME"), obj.getString("DO"),obj.getString("DH"), obj.getString("DL"), obj.getString("LTP"),obj.getString("PDC"),gVal));
                bseAdapter.notifyDataSetChanged();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void prepareFakeData() {

        bseList.add(new StockDataModel("name-1", "12", "12", "12", "12.6"));
        bseList.add(new StockDataModel("name-2", "14", "17", "14", "23"));
        bseList.add(new StockDataModel("name-3", "52", "35", "1", "562"));
        bseList.add(new StockDataModel("name-4", "62", "442", "62", "16.4"));
        bseList.add(new StockDataModel("name-5", "17", "1266", "6", "144"));


        bseAdapter.notifyDataSetChanged();

    }

    private void onlineRealData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_sensex,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("VOLL", "onResponse: " + response);
                        jsonParseSet(response);
                        pbMain.setVisibility(View.GONE);
                        llAllMain.setVisibility(View.VISIBLE);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "error " + error);

            }
        });

        queue.add(stringRequest);
    }

    private void jsonParseSet(String jsonText) {

        try {


            JSONObject obj = new JSONObject(jsonText);
            tvDH.setText(obj.getString("DH"));
            tvDO.setText(obj.getString("DO"));
            tvDL.setText(obj.getString("DL"));
            tvLTP.setText(obj.getString("LTP"));
            //tvName.setText(obj.getString("NAME"));
            String pdc=obj.getString("PDC");

            JSONArray graphValues = obj.getJSONArray("graph_values");
            Log.d(TAG, "jsonParseSet: " + graphValues);


            series.addPoint(new ValueLinePoint("Seen00",Float.parseFloat(pdc)));


            for (int i = 0; i < graphValues.length(); i++) {
                float a = Float.parseFloat(graphValues.getString(i));
                //Log.d(TAG, "val " + a);

                series.addPoint(new ValueLinePoint("Seen", a));

            }
            //series.addPoint(new ValueLinePoint("Seen00",Float.parseFloat(obj.getString("DL"))-200 ));

            // Log.d(TAG, "jsonParseSet: " + series);
            mCubicValueLineChart.addSeries(series);
            mCubicValueLineChart.setUseDynamicScaling(true);
            mCubicValueLineChart.startAnimation();


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

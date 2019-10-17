package com.frictionhacks.eqt;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hussain_chachuliya.customsearch.CustomSearch;
import com.hussain_chachuliya.customsearch.SearchAdapterHolder;

import org.eazegraph.lib.charts.ValueLineChart;
import org.eazegraph.lib.models.ValueLineSeries;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.android.volley.VolleyLog.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockSearchFragment extends Fragment {
    private SearchAdapterHolder holder;
    private final int REQ_CODE = 55;
    private String url_base = "https://92878288.ngrok.io/search?code=";
    private ValueLineSeries series;
    private ValueLineChart mCubicValueLineChart;
    private RequestQueue queue;


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
        View view = inflater.inflate(R.layout.fragment_stock_search, container, false);


        holder = new SearchAdapterHolder();
        holder.addAdapter(getListOfStrings(), REQ_CODE);
        queue = Volley.newRequestQueue(getContext());

        CustomSearch.start(StockSearchFragment.this, REQ_CODE, holder);


        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

      if(requestCode == REQ_CODE && resultCode == RESULT_OK) {
            //Toast.makeText(getContext(),"clicked item "+data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT), Toast.LENGTH_SHORT).show();
            //Log.d(TAG, "onActivityResult: "+data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));

            onlineStockSearch(data.getStringExtra(CustomSearch.CUSTOM_SEARCH_TEXT));

        }
        queue = Volley.newRequestQueue(getContext());

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
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        fragment.setArguments(bun);
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, fragment);
        ft.commit();
    }


    private void onlineStockSearch(String stockCode) {

        String url_search = url_base + stockCode;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_search,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("VOLL", "onResponse: " + response);
                        jsonParseSet(response);
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

            JSONArray gArray = obj.getJSONArray("graph_values");
            //Log.d(TAG, "jsonParseSet: " + graphValues);


            Bundle bun = new Bundle();
            bun.putString("name", obj.getString("NAME"));
            bun.putString("dh", obj.getString("DH"));
            bun.putString("dl", obj.getString("DL"));
            bun.putString("ltp", obj.getString("LTP"));
            bun.putString("pdc", obj.getString("PDC"));
            bun.putString("do", obj.getString("DO"));

            ArrayList<String> gVal = new ArrayList<>();
            for (int h = 0; h < gArray.length(); h++) {
                gVal.add(gArray.getString(h));
            }

            bun.putSerializable("gVal", gVal);
            //  series.addPoint(new ValueLinePoint("Seen00",Float.parseFloat(pdc)));


            loadFragment(new StockDetailFragment(), bun);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

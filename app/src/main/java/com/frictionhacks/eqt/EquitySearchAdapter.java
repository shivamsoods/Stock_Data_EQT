package com.frictionhacks.eqt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EquitySearchAdapter extends RecyclerView.Adapter<EquitySearchAdapter.MyViewHolder> {
    private List<StockDataModel> stockList;
    private View itemView;
    private CardView cardView;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName, stockPrice;
        CardView cardView;


        public MyViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.tv_equity_search_name);
            stockPrice = itemView.findViewById(R.id.tv_equity_search_price);
            cardView = itemView.findViewById(R.id.cv_equity_search);
        }
    }

    public EquitySearchAdapter(List<StockDataModel> stockList) {
        this.stockList = stockList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.equity_search_display, parent, false);


        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final StockDataModel stock = stockList.get(position);
        holder.stockName.setText((stock.getStockName()));
        holder.stockPrice.setText((stock.getLastTradedPrice()));


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bun = new Bundle();
                bun.putString("name", stock.getStockName());
                bun.putString("dh", stock.getDayHigh());
                bun.putString("do", stock.getDayOpen());
                bun.putString("ltp", stock.getLastTradedPrice());
                bun.putString("dl", stock.getDayLow());
                bun.putSerializable("gVal", stock.getgVal());
                bun.putSerializable("tVal", stock.gettVal());
                bun.putString("userRec", stock.getUserRec());

                loadFragment(new StockDetailFragment(), bun);

            }
        });
    }


    @Override
    public int getItemCount() {
        return stockList.size();
    }

    private void loadFragment(Fragment fragment, Bundle bun) {
        AppCompatActivity activity = (AppCompatActivity) itemView.getContext();
        fragment.setArguments(bun);
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, 0, 0);
        ft.replace(R.id.fl_main, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}

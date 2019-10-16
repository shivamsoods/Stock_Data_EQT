package com.frictionhacks.eqt;

import android.content.Intent;
import android.net.Uri;

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
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder> {
    private List<StockDataModel> stockList;
    private View itemView;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName, stockPrice;
        CardView cardView;

       public   MyViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.tv_display_stock_name);
            stockPrice = itemView.findViewById(R.id.tv_display_stock_price);
            cardView=itemView.findViewById(R.id.cv_home_stock);
        }
    }

    public StockAdapter(List<StockDataModel> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_home_display, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        StockDataModel stock = stockList.get(position);
        holder.stockName.setText((stock.getStockName()));
        holder.stockPrice.setText((stock.getStockPrice()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bun=new Bundle();
                bun.putString("name","HDFC_m");

                loadFragment(new StockDetailFragment(),bun);

            }
        });
    }


    @Override
    public int getItemCount() {
        return stockList.size();
    }

    private void loadFragment(Fragment fragment,Bundle bun) {
        AppCompatActivity activity= (AppCompatActivity) itemView.getContext();
        fragment.setArguments(bun);
        FragmentTransaction ft=activity.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fl_main, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}

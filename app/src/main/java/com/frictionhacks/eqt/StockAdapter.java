package com.frictionhacks.eqt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder> {
    private List<StockDataModel> stockList;


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName, stockPrice;

        MyViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.tv_display_stock_name);
            stockPrice = itemView.findViewById(R.id.tv_display_stock_price);

        }
    }

    public StockAdapter(List<StockDataModel> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_home_display, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StockDataModel stock = stockList.get(position);
        holder.stockName.setText((stock.getStockName()));
        holder.stockPrice.setText((stock.getStockPrice()));

    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }


}

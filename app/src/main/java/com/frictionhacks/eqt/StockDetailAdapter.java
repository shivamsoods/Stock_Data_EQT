package com.frictionhacks.eqt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StockDetailAdapter extends RecyclerView.Adapter<StockDetailAdapter.MyViewHolder> {
    private List<StockDataModel> stockList;


    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView stockName,dayOpen, dayHigh, dayLow, lastTradedPrice;

        public   MyViewHolder(View itemView) {
            super(itemView);
            stockName = itemView.findViewById(R.id.tv_display_stock_name);
            dayOpen = itemView.findViewById(R.id.tv_detil_do_value);
            dayHigh=itemView.findViewById(R.id.tv_detail_dh_value);
            dayLow=itemView.findViewById(R.id.tv_detail_dl_value);
            lastTradedPrice=itemView.findViewById(R.id.tv_detail_ltp_value);

        }
    }

    public StockDetailAdapter(List<StockDataModel> stockList) {
        this.stockList = stockList;
    }

    @NonNull
    @Override
    public StockDetailAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_detail_display, parent, false);

        return new StockDetailAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        StockDataModel stock = stockList.get(position);
        holder.stockName.setText((stock.getStockName()));
        holder.dayHigh.setText((stock.getDayHigh()));
        holder.dayLow.setText((stock.getDayLow()));
        holder.dayOpen.setText((stock.getDayOpen()));
        holder.lastTradedPrice.setText((stock.getLastTradedPrice()));


    }


    @Override
    public int getItemCount() {
        return stockList.size();
    }


}

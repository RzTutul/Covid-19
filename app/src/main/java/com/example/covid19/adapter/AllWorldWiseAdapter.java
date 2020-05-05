package com.example.covid19.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.R;
import com.example.covid19.pojo.CountyCaseData;

import java.util.ArrayList;
import java.util.List;

public class AllWorldWiseAdapter extends RecyclerView.Adapter<AllWorldWiseAdapter.WorldWiseCaseViewHolder> {
private Context context;
private List<CountyCaseData> list= new ArrayList<>();

    public AllWorldWiseAdapter(Context context, List<CountyCaseData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public WorldWiseCaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_info,parent,false);

        return new WorldWiseCaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorldWiseCaseViewHolder holder, int position) {
        CountyCaseData countyCaseData = list.get(position);

        holder.Header.setText(countyCaseData.getHeader());
        holder.value.setText(countyCaseData.getValue());

        holder.cardView.setCardBackgroundColor(Color.parseColor(countyCaseData.getColor()));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class WorldWiseCaseViewHolder extends RecyclerView.ViewHolder {

        TextView Header,value;
        CardView cardView;


        public WorldWiseCaseViewHolder(@NonNull View itemView) {
            super(itemView);
            Header = itemView.findViewById(R.id.textViewHader);
            value = itemView.findViewById(R.id.textViewcasevalue);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}

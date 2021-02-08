package com.example.covid19.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covid19.OnlineWebView;
import com.example.covid19.R;
import com.example.covid19.pojo.CountryWiseCasePojo;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AllCountryWiseAdpater extends RecyclerView.Adapter<AllCountryWiseAdpater.WorldWiseCaseViewHolder> {
private Context context;
private List<CountryWiseCasePojo> list;

    public AllCountryWiseAdpater(Context context, List<CountryWiseCasePojo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public WorldWiseCaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview_for_allcountries,parent,false);

        return new WorldWiseCaseViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull WorldWiseCaseViewHolder holder, int position) {
        CountryWiseCasePojo countyCaseData = list.get(position);
            holder.countryContentTV.setText(countyCaseData.getContinent());

        holder.countyNameTV.setText(countyCaseData.getCountry());
        holder.totalCaseTV.setText(countyCaseData.getCases());
        holder.totalDeathTV.setText(countyCaseData.getDeaths());
        holder.recoverdTV.setText(countyCaseData.getRecovered());
        holder.activeCaseTV.setText(countyCaseData.getActive());
        holder.CriticalTV.setText(countyCaseData.getCritical());
        holder.todayCaseTV.setText(countyCaseData.getTodayCases());
        holder.todayDeathTV.setText(countyCaseData.getTodayDeaths());
        holder.totalTestTV.setText(countyCaseData.getTests());
        holder.deathPermilTV.setText(countyCaseData.getDeathsPerOneMillion());
        holder.testPermillTV.setText(countyCaseData.getTestsPerOneMillion());
        holder.casePerMillTV.setText(countyCaseData.getCasesPerOneMillion());

       // Log.i(TAG, "onBindViewHolder: "+countyCaseData.getCountryInfo().getFlag()) ;
       Picasso.get().load(countyCaseData.getCountryInfo().getFlag()).into(holder.imageView);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               OnlineWebView.countyName=countyCaseData.getCountry();
               Navigation.findNavController(holder.itemView).navigate(R.id.onlineWebView);
           }
       });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class WorldWiseCaseViewHolder extends RecyclerView.ViewHolder {

        TextView countyNameTV,totalCaseTV,totalDeathTV,recoverdTV,activeCaseTV,CriticalTV,todayCaseTV,todayDeathTV,totalTestTV;
       TextView countryContentTV,deathPermilTV,casePerMillTV,testPermillTV;
        CircularImageView imageView;


        public WorldWiseCaseViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.flagimage);
            countyNameTV = itemView.findViewById(R.id.textViewcountryname);
            totalCaseTV = itemView.findViewById(R.id.c_totalCase);
            totalDeathTV = itemView.findViewById(R.id.c_totalDeaths);
            recoverdTV = itemView.findViewById(R.id.c_totalRecoverTV);
            activeCaseTV = itemView.findViewById(R.id.c_activeTV);
            CriticalTV = itemView.findViewById(R.id.c_criticalCaseTV);
            todayCaseTV = itemView.findViewById(R.id.c_todayCaseTV);
            todayDeathTV = itemView.findViewById(R.id.c_todaydeathsTV);
            totalTestTV = itemView.findViewById(R.id.c_totalTestTV);
            countryContentTV = itemView.findViewById(R.id.contentTV);
            deathPermilTV = itemView.findViewById(R.id.c_deathPerMillTV);
            casePerMillTV = itemView.findViewById(R.id.c_casePerMillTV);
            testPermillTV = itemView.findViewById(R.id.c_testPerMillTV);


        }
    }
}

package com.example.covid_19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyCustomAdopter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListfiltered;

    public MyCustomAdopter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_costum_item, countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListfiltered = countryModelList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_costum_item,null,true);

        TextView countryName = view.findViewById(R.id.tvCountryName);
        ImageView imageView = view.findViewById(R.id.imageFlage);

        countryName.setText(countryModelListfiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListfiltered.get(position).getFlag()).into(imageView);


        return view;
    }

    @Override
    public int getCount() {
        return countryModelListfiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                }
                else{
                    List<CountryModel> resultModel = new ArrayList<>();
                    String searchStr = charSequence.toString().toLowerCase();

                    for(CountryModel itemModel : countryModelList){
                        if(itemModel.getCountry().toLowerCase().contains(searchStr)){
                            resultModel.add(itemModel);
                        }
                        filterResults.count = resultModel.size();
                        filterResults.values = resultModel;
                    }
                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                countryModelListfiltered = (List<CountryModel>) filterResults.values;
                AffectedCountries.countryModelList = (List<CountryModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };

        return filter;

    }
}

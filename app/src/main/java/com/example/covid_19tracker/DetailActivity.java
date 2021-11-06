package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private int position;
    TextView tvCountry, tvCases, tvTodayCases, tvDeaths, tvTodayDeaths, tvRecovered, tvActive,
    tvCritical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);


        getSupportActionBar().setTitle("Details of "+AffectedCountries.countryModelList.get(position).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvActive = findViewById(R.id.tvActive);
        tvCritical = findViewById(R.id.tvCritical);

        tvCountry.setText(AffectedCountries.countryModelList.get(position).getCountry());
        tvCases.setText(AffectedCountries.countryModelList.get(position).getCases());
        tvTodayCases.setText(AffectedCountries.countryModelList.get(position).getTodayCases());
        tvDeaths.setText(AffectedCountries.countryModelList.get(position).getDeaths());
        tvTodayDeaths.setText(AffectedCountries.countryModelList.get(position).getTodayDeaths());
        tvRecovered.setText(AffectedCountries.countryModelList.get(position).getRecovered());
        tvActive.setText(AffectedCountries.countryModelList.get(position).getActive());
        tvCritical.setText(AffectedCountries.countryModelList.get(position).getCritical());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }




}
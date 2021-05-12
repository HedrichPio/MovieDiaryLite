package com.example.moviediarylite.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moviediarylite.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openMovieFunction(View view){

        Intent movieIntent = new Intent(HomeActivity.this, MovieFunctionsActivity.class);
        startActivity(movieIntent);

    }

    public void openTvSeriesFunction(View view){

        Intent tvseriesIntent = new Intent(HomeActivity.this, TvSeriesFunctionsActivity.class);
        startActivity(tvseriesIntent);

    }
}
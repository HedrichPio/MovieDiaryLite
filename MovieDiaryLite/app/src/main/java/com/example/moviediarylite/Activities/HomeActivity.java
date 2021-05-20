package com.example.moviediarylite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moviediarylite.R;

public class HomeActivity extends AppCompatActivity {

    Animation top_animation;

    Button button_movies_ha;
    Button button_tvseries_ha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);


        button_movies_ha = findViewById(R.id.moviesbutton_home);
        button_movies_ha.setAnimation(top_animation);

        button_tvseries_ha = findViewById(R.id.tvseriesbutton_home);
        button_tvseries_ha.setAnimation(top_animation);





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
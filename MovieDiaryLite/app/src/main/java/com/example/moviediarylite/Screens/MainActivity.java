package com.example.moviediarylite.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.moviediarylite.R;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Animation bottom_animation;
    Animation top_animation;

    TextView logo_movie;
    TextView logo_diary;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        logo_movie = findViewById(R.id.logomovie_main);
        logo_movie.setAnimation(bottom_animation);

        logo_diary = findViewById(R.id.logodiary_main);
        logo_diary.setAnimation(top_animation);



        handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        },3000);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
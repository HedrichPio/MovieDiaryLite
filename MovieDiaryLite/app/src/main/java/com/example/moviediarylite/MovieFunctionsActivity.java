package com.example.moviediarylite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MovieFunctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_functions);
    }


    public void addMovie(View view){

        Intent addmovie = new Intent(this,AddMovieActivity.class);
        startActivity(addmovie);

    }
}
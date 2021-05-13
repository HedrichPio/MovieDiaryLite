package com.example.moviediarylite.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moviediarylite.R;

public class MovieFunctionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_functions);
    }


    public void openAddMovie(View view){

        Intent addmovie = new Intent(this,AddMovieActivity.class);
        startActivity(addmovie);

    }

    public void openViewAllMovies(View view){

        Intent viewAllMovies = new Intent(this,ViewAllMoviesActivity.class);
        startActivity(viewAllMovies);

    }

    public void openViewFavouriteMovies(View view){

        Intent viewFavouriteMovies = new Intent(this,FavouritesActivity.class);
        startActivity(viewFavouriteMovies);

    }


}
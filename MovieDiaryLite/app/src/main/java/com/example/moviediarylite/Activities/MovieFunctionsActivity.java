package com.example.moviediarylite.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moviediarylite.R;

public class MovieFunctionsActivity extends AppCompatActivity {

    Animation top_animation;

    ImageView movietape_imageview_mfa;
    Button button_addmovie_mfa;
    Button button_viewmovies_mfa;
    Button button_favourites_mfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_functions);


        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        button_addmovie_mfa = findViewById(R.id.addmoviebutton_functions);
        button_addmovie_mfa.setAnimation(top_animation);

        button_viewmovies_mfa = findViewById(R.id.viewmoviesbutton_functions);
        button_viewmovies_mfa.setAnimation(top_animation);

        button_favourites_mfa = findViewById(R.id.favouritesbutton_functions);
        button_favourites_mfa.setAnimation(top_animation);

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
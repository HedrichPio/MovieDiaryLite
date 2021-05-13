package com.example.moviediarylite.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;
import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    ListView fav_movies_listview_fa;

    DatabaseHelper DB = new DatabaseHelper(this);
    ArrayList<Movie> movielist = new ArrayList<>();

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        fav_movies_listview_fa = findViewById(R.id.favourites_listview_favourites);


        for(Movie m:DB.getAllMovies()){

            if(m.getFavourite().equalsIgnoreCase("yes")){
                movielist.add(m);
            }

        }

        if (!movielist.isEmpty()) {
            // Create the adapter to convert the array to views
            MovieAdapter adapter = new MovieAdapter(this, movielist);

            // Attach the adapter to a ListView
            fav_movies_listview_fa.setAdapter(adapter);

            fav_movies_listview_fa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // TODO Auto-generated method stub

                    Movie selectedMovie = adapter.getItem(position);

                    showMovieDetails(selectedMovie);

                }
            });

        } else {

            alerts.showAlertDialog(this,"No Movies added", "Please add atleast one Movie to Display");

        }

    }

    private void showMovieDetails(Movie selectedMovie){

        alerts.showAlertDialog(this, "Movie",
                "Title :"+ selectedMovie.getTitle() +"\n" +
                        "Year :"+ selectedMovie.getYear() +"\n" +
                        "Genre :"+ selectedMovie.getGenre() +"\n" +
                        "Rating :"+ selectedMovie.getFavourite() +"\n" +
                        "is Favourites :"+ selectedMovie.getFavourite());

    }
}
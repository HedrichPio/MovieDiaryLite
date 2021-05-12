package com.example.moviediarylite.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;
import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;

import java.util.ArrayList;

public class ViewAllMoviesActivity extends AppCompatActivity {


    ListView allmovieslistview_vam;

    DatabaseHelper DB = new DatabaseHelper(this);
    ArrayList<Movie> movielist = new ArrayList<>();

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_movies);

        allmovieslistview_vam = findViewById(R.id.allmovieslistview_viewallmovies);

        movielist = DB.getAllMovies();

        if (!movielist.isEmpty()) {
            // Create the adapter to convert the array to views
            MovieAdapter adapter = new MovieAdapter(this, movielist);

            // Attach the adapter to a ListView
            allmovieslistview_vam.setAdapter(adapter);

            allmovieslistview_vam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
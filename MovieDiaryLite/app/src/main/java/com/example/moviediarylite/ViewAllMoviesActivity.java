package com.example.moviediarylite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;

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

        } else {

            alerts.showAlertDialog(this,"No Movies added", "Please add atleast one Movie to Display");

        }

    }
}
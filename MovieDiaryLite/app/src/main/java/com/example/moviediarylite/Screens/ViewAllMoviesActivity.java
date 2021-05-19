package com.example.moviediarylite.Screens;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;


import com.example.moviediarylite.Comparators.AlphabeticalSort;
import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;
import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;

import java.util.ArrayList;

public class ViewAllMoviesActivity extends AppCompatActivity {


    MovieAdapter adapter;

    ListView allmovieslistview_vam;
    SearchView searchview_vam;

    DatabaseHelper DB = new DatabaseHelper(this);
    ArrayList<Movie> movielist = new ArrayList<>();

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_movies);

        allmovieslistview_vam = findViewById(R.id.allmovieslistview_viewallmovies);
        searchview_vam = findViewById(R.id.searchview_allmovies);

        movielist = DB.getAllMovies();

        if (!movielist.isEmpty()) {

            setAdaptertoListView(movielist);

        }
        else {

            alerts.showAlertDialog(this,"No Movies added", "Please add atleast one Movie to Display");

        }


        searchview_vam.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                ArrayList<Movie> filteredList = new ArrayList<>();

                for(Movie m : DB.getAllMovies()){
                    if(m.getTitle().toLowerCase().contains(newText.toLowerCase())){
                        filteredList.add(m);
                    }
                }

                if (!filteredList.isEmpty()) {

                    setAdaptertoListView(filteredList);

                }
                else {

                    alerts.showAlertDialog(ViewAllMoviesActivity.this,"No Results Found", "No Movies matched with the Keyword.");

                }
                return false;
            }
        });


    }



    private void setAdaptertoListView(ArrayList<Movie> movieArrayList){


        adapter = new MovieAdapter(this, movieArrayList);

            allmovieslistview_vam.setAdapter(adapter);

            allmovieslistview_vam.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                    Movie selectedMovie = adapter.getItem(position);

                    showMovieDetails(selectedMovie);

                }
            });
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
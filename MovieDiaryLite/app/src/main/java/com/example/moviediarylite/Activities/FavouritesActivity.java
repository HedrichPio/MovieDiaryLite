package com.example.moviediarylite.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;
import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;
import com.example.moviediarylite.SearchFilters.Filters;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    Animation top_animation;

    ListView fav_movies_listview_fa;

    SearchView searchview_fa;

    MovieAdapter adapter;

    Filters filters = new Filters();


    DatabaseHelper DB = new DatabaseHelper(this);
    ArrayList<Movie> movielist = new ArrayList<>();

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        fav_movies_listview_fa = findViewById(R.id.favourites_listview_favourites);
        fav_movies_listview_fa.setAnimation(top_animation);

        searchview_fa = findViewById(R.id.searchview_favourites);
        searchview_fa.setAnimation(top_animation);


        movielist = DB.getAllFavouriteMovies();

        checkandSetAdapter(movielist, "No Movies added", "Please add atleast one Movie to Display");

        setSearchviewListener();
    }




    private void checkandSetAdapter(ArrayList<Movie> movieArraylist, String alertType, String alertMessage){

        if (!movieArraylist.isEmpty()) {
            // Create the adapter to convert the array to views
            setAdaptertoListView(movieArraylist);

        } else {

            alerts.showAlertDialog(this,alertType, alertMessage);
        }

    }


    private void setAdaptertoListView(ArrayList<Movie> movieArrayList){


        adapter = new MovieAdapter(this, movieArrayList);

        fav_movies_listview_fa.setAdapter(adapter);

        fav_movies_listview_fa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Movie selectedMovie = adapter.getItem(position);

                showMovieDetails(selectedMovie);

            }
        });
    }



    private void setSearchviewListener(){

        searchview_fa.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Movie> filteredList = new ArrayList<>();

                filteredList = filters.FilterbyAny(DB.getAllFavouriteMovies(),newText);


                checkandSetAdapter(filteredList,"No Results Found","No Movies matched with the Keyword.");

                return false;
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
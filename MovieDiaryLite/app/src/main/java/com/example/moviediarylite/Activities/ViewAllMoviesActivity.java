package com.example.moviediarylite.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;


import com.example.moviediarylite.Comparators.YearSort;
import com.example.moviediarylite.Components.AlertDialogandToastMessages;
import com.example.moviediarylite.Components.MovieAdapter;
import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;
import com.example.moviediarylite.SearchFilters.Filters;

import java.util.ArrayList;

public class ViewAllMoviesActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Animation top_animation;

    MovieAdapter adapter;

    ListView allmovieslistview_vam;
    SearchView searchview_vam;
    Button filter_button_vam;

    DatabaseHelper DB = new DatabaseHelper(this);
    ArrayList<Movie> movielist = new ArrayList<>();

    Filters filters = new Filters();

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_movies);

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);


        allmovieslistview_vam = findViewById(R.id.allmovieslistview_viewallmovies);
        allmovieslistview_vam.setAnimation(top_animation);

        searchview_vam = findViewById(R.id.searchview_allmovies);
        searchview_vam.setAnimation(top_animation);

        filter_button_vam = findViewById(R.id.filter_button_allmovies);
        filter_button_vam.setAnimation(top_animation);


        movielist = DB.getAllMovies();

        checkandSetAdapter(movielist, "No Movies added", "Please add atleast one Movie to Display");

        setSearchviewListener();




    }


    private void checkandSetAdapter(ArrayList<Movie> movieArraylist, String alertType, String alertMessage){

        if (!movieArraylist.isEmpty()) {
            // Create the adapter to convert the array to views
            setAdaptertoListView(movieArraylist);

        } else {
            alerts.showAlertDialog(this,alertType, alertMessage);
            setAdaptertoListView(movieArraylist);

        }

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



    private void setSearchviewListener(){

        searchview_vam.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Movie> filteredList = new ArrayList<>();

                filteredList = filters.FilterbyAny(DB.getAllMovies(),newText);

                checkandSetAdapter(filteredList,"No Results Found","No Movies matched with the Keyword.");

                return false;
            }
        });

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.sortbyname:

                checkandSetAdapter(DB.getAllMovies(),"No Results Found","No Movies matched with the Keyword.");
                return true;

            case R.id.sortbyyear:

                ArrayList<Movie> mlist = DB.getAllMovies();
                mlist.sort(new YearSort());

                checkandSetAdapter(mlist,"No Results Found","No Movies matched with the Keyword.");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void showFilters(View view) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.inflate(R.menu.filter_menu);
        popupMenu.show();

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){

            case R.id.action:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"action"),"No Results Found","Movies with genre action not yet added.");
                return true;

            case R.id.ai:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"ai/it"),"No Results Found","Movies with genre AI/IT not yet added.");
                return true;

            case R.id.animation:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"animation"),"No Results Found","Movies with genre animation not yet added.");
                return true;

            case R.id.cartel:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"cartel"),"No Results Found","Movies with genre cartel not yet added.");
                return true;

            case R.id.comedy:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"comedy"),"No Results Found","Movies with genre comedy not yet added.");
                return true;

            case R.id.family:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"family"),"No Results Found","Movies with genre family not yet added.");
                return true;

            case R.id.heist:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"heist"),"No Results Found","Movies with genre heist not yet added.");
                return true;

            case R.id.horror:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"horror"),"No Results Found","Movies with genre horror not yet added.");
                return true;

            case R.id.racing:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"racing"),"No Results Found","Movies with genre racing not yet added.");
                return true;

            case R.id.romance:
                checkandSetAdapter(filters.FilterbyGenre(DB.getAllMovies(),"romance"),"No Results Found","Movies with genre romance not yet added.");
                return true;

            default:
                return false;

        }
    }






    private void showMovieDetails(Movie selectedMovie){

        alerts.showAlertDialog(this, "Movie",
                "Title :"+ selectedMovie.getTitle() +"\n" +
                        "Year :"+ selectedMovie.getYear() +"\n" +
                        "Genre :"+ selectedMovie.getGenre() +"\n" +
                        "Rating :"+ selectedMovie.getRating() +"\n" +
                        "is Favourites :"+ selectedMovie.getFavourite());

    }



}
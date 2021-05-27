package com.example.moviediarylite.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.moviediarylite.API.NetworkUtils;
import com.example.moviediarylite.Components.MovieAdapterAPI;
import com.example.moviediarylite.MovieAPI;
import com.example.moviediarylite.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MovieFunctionsActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";

    Animation top_animation;

    RecyclerView popmovies_recyclerview_mf;

    MovieAdapterAPI adapter;

    ImageView movietape_imageview_mfa;
    Button button_addmovie_mfa;
    Button button_viewmovies_mfa;
    Button button_favourites_mfa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_functions);


        popmovies_recyclerview_mf = findViewById(R.id.popmovies_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(MovieFunctionsActivity.this,LinearLayoutManager.HORIZONTAL,false);

        popmovies_recyclerview_mf.setLayoutManager(layoutManager);
        popmovies_recyclerview_mf.setItemAnimator(new DefaultItemAnimator());

        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);

        button_addmovie_mfa = findViewById(R.id.addmoviebutton_functions);
        button_addmovie_mfa.setAnimation(top_animation);

        button_viewmovies_mfa = findViewById(R.id.viewmoviesbutton_functions);
        button_viewmovies_mfa.setAnimation(top_animation);

        button_favourites_mfa = findViewById(R.id.favouritesbutton_functions);
        button_favourites_mfa.setAnimation(top_animation);


        getPopularMovies();

    }



    private void getPopularMovies() {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                extractMoviesfromJson(NetworkUtils.getMovieIDasJson());
            }
        });
        t1.start();


    }

    ArrayList<MovieAPI> movieAPIArrayList = new ArrayList<>();

    //method to extract the ids from JSON
    private void extractMoviesfromJson(String s) {

        ArrayList<String> imgurlsList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(s);

            JSONArray jsonArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < 10; i++) {


                String m_id = jsonArray.getJSONObject(i).getString("id");
                String mtitle = jsonArray.getJSONObject(i).getString("title");
                String myear = jsonArray.getJSONObject(i).getString("year");
                String m_img = jsonArray.getJSONObject(i).getString("image");
                String m_rating = jsonArray.getJSONObject(i).getString("imDbRating");

                imgurlsList.add(m_img);

                MovieAPI movieAPI = new MovieAPI(m_id, mtitle,myear,m_rating);
                movieAPIArrayList.add(movieAPI);

            }

            convertUrlstoBitmap(imgurlsList);

//            Thread t2 = new Thread(new Runnable()  {
//                @Override
//                public void run() {
//                }
//            });
//            t2.start();

            Log.d(TAG, movieAPIArrayList.toString());


        } catch (Exception e) {

            // update the UI to show failed results.
            //updateUI("JSON Retrieval Failed");

            e.printStackTrace();

        }
    }



    private void convertUrlstoBitmap(ArrayList<String> imgurlsList) {

        Thread thread = new Thread(new Runnable()  {
            @Override
            public void run() {

                InputStream is = null;
                Bitmap imgbitmap = null;

                ArrayList<Bitmap> bitmapArrayList = new ArrayList<>();

                try {

                    for(String url: imgurlsList){

                        is = new URL(url).openStream();

                        imgbitmap = BitmapFactory.decodeStream(is);

                        bitmapArrayList.add(imgbitmap);

                        Log.d(TAG,"success");

                    }

                    setBitmapstoMovies(movieAPIArrayList,bitmapArrayList);

                } catch (IOException e) {

                    Log.d(TAG,"in catch");
                    //e.printStackTrace();
                }
            }
        });
        thread.start();

    }


    private void setBitmapstoMovies(ArrayList<MovieAPI> movieAPIArrayList, ArrayList<Bitmap> bitmapArrayList) {

        for(int i=0;i<movieAPIArrayList.size();i++){

            movieAPIArrayList.get(i).setImgUrl(bitmapArrayList.get(i));

        }

        setRecyclerview(movieAPIArrayList);

    }



    private void setRecyclerview(ArrayList<MovieAPI> movieAPIArrayList){

        adapter = new MovieAdapterAPI(MovieFunctionsActivity.this, movieAPIArrayList);

        runOnUiThread(new Runnable() {
            public void run() {
                popmovies_recyclerview_mf.setAdapter(adapter);
            }
        });
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
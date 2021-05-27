package com.example.moviediarylite.API;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {


    //private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String TAG = "MyActivity";


    //method to get the json objects from the internet
    private static String getRatingInfo(String url){

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JSONString = null;


        try {

            URL requestURL = new URL(url);

            urlConnection = (HttpURLConnection) requestURL.openConnection();

            urlConnection.setRequestMethod("GET");

            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            // Create a buffered reader from that input stream.
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Use a StringBuilder to hold the incoming response.
            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                // Since it's JSON, adding a newline isn't necessary (it won't
                // affect parsing) but it does make debugging a *lot* easier
                // if you print out the completed buffer for debugging.
                builder.append("\n");
            }

            if (builder.length() == 0) {
                // Stream was empty. No point in parsing.
                return null;
            }

            JSONString = builder.toString();
        }
        catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d(TAG, JSONString);
        return JSONString;
    }



    //method to get the json object with all the titles similar to the given title
    public static String getMovieIDasJson(){

        //String url = "https://imdb-api.com/en/API/SearchTitle/k_buwntvj4/"+ movie_title;
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_buwntvj4";

        return getRatingInfo(url);

    }



    //method to get the json object with ratings for the given ID
    public static String getMovieRatingasJson(String movie_ID){

        String url = "https://imdb-api.com/en/API/UserRatings/k_buwntvj4/" + movie_ID;

        return getRatingInfo(url);
    }
}


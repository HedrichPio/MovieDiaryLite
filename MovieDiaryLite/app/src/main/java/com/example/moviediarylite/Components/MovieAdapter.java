package com.example.moviediarylite.Components;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.moviediarylite.Movie;
import com.example.moviediarylite.R;

import java.util.ArrayList;

public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        super(context, 0, movieList);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item, parent, false);
        }

        // Lookup view for data population
        TextView movietitle_textview = (TextView) convertView.findViewById(R.id.title_textview_mi);
        TextView movieyear_textview = (TextView) convertView.findViewById(R.id.year_textview_mi);
        TextView moviegenre_textview = (TextView) convertView.findViewById(R.id.genre_textview_mi);


        // Populate the data into the template view using the data object
        movietitle_textview.setText(movie.getTitle());
        movieyear_textview.setText(String.valueOf(movie.getYear()));
        moviegenre_textview.setText(movie.getGenre());

        // Return the completed view to render on screen
        return convertView;
    }
}

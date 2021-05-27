package com.example.moviediarylite.Components;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviediarylite.MovieAPI;
import com.example.moviediarylite.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MovieAdapterAPI extends RecyclerView.Adapter<MovieAdapterAPI.ViewHolder> {

    private static final String TAG = "debug";

    ArrayList<MovieAPI> movieAPIArrayList;
    Context context;

    public MovieAdapterAPI(Context context,ArrayList<MovieAPI> movieAPIArrayList){

        this.context = context;
        this.movieAPIArrayList = movieAPIArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.api_movie_item,parent,false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MovieAPI movieAPI = movieAPIArrayList.get(position);

        holder.titleTextview.setText(movieAPI.getTitle());
        holder.yearTextview.setText(movieAPI.getYear());
        holder.ratingTextview.setText(movieAPI.getRating());
        holder.imageView.setImageBitmap(movieAPI.getImgUrl());

//        Thread thread = new Thread(new Runnable()  {
//            @Override
//            public void run() {
//
//            }
//        });
//        thread.start();
    }


    @Override
    public int getItemCount() {
        return movieAPIArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView titleTextview;
        TextView yearTextview;
        TextView ratingTextview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.api_movieimage_imageview);
            titleTextview = itemView.findViewById(R.id.api_movietitle_textview);
            yearTextview = itemView.findViewById(R.id.api_movieyear_textview);
            ratingTextview = itemView.findViewById(R.id.api_movierating_textview);
        }
    }


}

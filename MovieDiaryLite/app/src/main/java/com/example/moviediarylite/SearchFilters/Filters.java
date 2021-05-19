package com.example.moviediarylite.SearchFilters;

import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;

import java.util.ArrayList;

public class Filters {



    public ArrayList<Movie> FilterbyAny(ArrayList<Movie> movieArrayList, String query){


        ArrayList<Movie> filteredList = new ArrayList<>();

        for(Movie m : movieArrayList){
            if(m.getTitle().toLowerCase().contains(query.toLowerCase())){
                filteredList.add(m);
            }
        }

        return filteredList;

    }


}

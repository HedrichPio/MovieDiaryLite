package com.example.moviediarylite.SearchFilters;

import com.example.moviediarylite.Databases.DatabaseHelper;
import com.example.moviediarylite.Movie;

import java.util.ArrayList;

public class Filters {



    public ArrayList<Movie> FilterbyAny(ArrayList<Movie> movieArrayList, String query){


        ArrayList<Movie> filteredList = new ArrayList<>();

        String queryLower = query.toLowerCase();

        for(Movie m : movieArrayList){
            if(m.getTitle().toLowerCase().contains(queryLower)||m.getGenre().toLowerCase().contains(queryLower)||String.valueOf(m.getYear()).contains(queryLower)){
                filteredList.add(m);
            }
        }

        return filteredList;

    }



    public ArrayList<Movie> FilterbyGenre(ArrayList<Movie> movieArrayList, String genre){


        ArrayList<Movie> filteredList = new ArrayList<>();

        for(Movie m : movieArrayList){
            if(m.getGenre().equalsIgnoreCase(genre)){
                filteredList.add(m);
            }
        }

        return filteredList;

    }


}

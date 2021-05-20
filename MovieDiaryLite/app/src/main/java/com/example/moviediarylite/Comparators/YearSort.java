package com.example.moviediarylite.Comparators;

import com.example.moviediarylite.Movie;

import java.util.Comparator;

public class YearSort implements Comparator<Movie> {

    //comparator to sort movies in alphabetical order

    @Override
    public int compare(Movie o1, Movie o2) {


        int compare = o1.getYear() - o2.getYear();

        if (compare < 0){
            return -1;
        }
        else if (compare > 0) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

package com.example.moviediarylite.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.moviediarylite.Comparators.AlphabeticalSort;
import com.example.moviediarylite.Movie;

import java.util.ArrayList;

    public class DatabaseHelper extends SQLiteOpenHelper {

        //initialise db name, table name and columns
        public static final String DATABASE_NAME = "movie.db";
        public static final String TABLE_NAME_MOV = "movies_table";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "TITLE";
        public static final String COL_3 = "YEAR";
        public static final String COL_4 = "GENRE";
        public static final String COL_5 = "RATING";
        public static final String COL_6 = "FAVOURITE";

        public static final String TABLE_NAME_TV = "tvshows_table";
        public static final String COL_7 = "SEASON";


        static final private int DB_VER = 1;

        SQLiteDatabase myDB;


        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DB_VER);

        }


        //create table with columns
        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("create table "+ TABLE_NAME_MOV +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "TITLE TEXT, " +
                    "YEAR INTEGER, " +
                    "GENRE TEXT, " +
                    "RATING INTEGER, " +
                    "FAVOURITE TEXT)");

            db.execSQL("create table "+ TABLE_NAME_TV +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "TITLE TEXT, " +
                    "SEASON INTEGER, " +
                    "GENRE TEXT, " +
                    "RATING INTEGER, " +
                    "FAVOURITE TEXT)");

        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_MOV);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TV);
            onCreate(db);

        }


        //method to add a user entered movie to the database
        public Boolean addMovietoDB(Movie movie){

            myDB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_2,movie.getTitle());
            contentValues.put(COL_3,movie.getYear());
            contentValues.put(COL_4,movie.getGenre());
            contentValues.put(COL_5,movie.getRating());
            contentValues.put(COL_6,movie.getFavourite());

            long result = myDB.insert(TABLE_NAME_MOV,null,contentValues);

            if(result==-1){
                return false;
            }
            else {
                return true;
            }

        }


//  method to get all the movies in the database into an arraylist
        @RequiresApi(api = Build.VERSION_CODES.N)
        public ArrayList<Movie> getAllMovies(){

            ArrayList<Movie> movieList = new ArrayList<>();

            String selectStm = "SELECT * FROM " + TABLE_NAME_MOV;

            myDB = this.getWritableDatabase();

            Cursor cursor = myDB.rawQuery(selectStm, null);

            if(cursor.moveToFirst()) {
                do{
                    Movie movie = new Movie();
                    movie.setTitle(cursor.getString(1));
                    movie.setYear(cursor.getInt(2));
                    movie.setGenre(cursor.getString(3));
                    movie.setRating(cursor.getInt(4));
                    movie.setFavourite(cursor.getString(5));

                    movieList.add(movie);

                }while (cursor.moveToNext());
            }

            movieList.sort(new AlphabeticalSort());

            return movieList;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        public ArrayList<Movie> getAllFavouriteMovies(){

            ArrayList<Movie> favList = new ArrayList<>();

            for(Movie m:getAllMovies()){

                if(m.getFavourite().equalsIgnoreCase("yes")){
                    favList.add(m);
                }

            }
            return favList;

        }


        //method to make a movie as favourite
        public Boolean updateFavourites(String title, int isFavourite){

            myDB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_6,isFavourite);

            long result = myDB.update(TABLE_NAME_MOV, contentValues, "TITLE = ?", new String[]{ title });

            if(result==-1){
                return false;
            }
            else {
                return true;
            }

        }


        //method to update a movie details
        public Boolean updateMovie(String title, Movie movie){

            myDB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put(COL_2,movie.getTitle());
            contentValues.put(COL_3,movie.getYear());
            contentValues.put(COL_4,movie.getGenre());
            contentValues.put(COL_5,movie.getRating());
            contentValues.put(COL_6,movie.getFavourite());

            long result = myDB.update(TABLE_NAME_MOV, contentValues, "TITLE = ?", new String[]{ title });

            if(result==-1){
                return false;
            }
            else {
                return true;
            }

        }


        //method to delete a movie
        public void deleteMovie(String name){

            myDB = this.getWritableDatabase();

            String query = "DELETE FROM " + TABLE_NAME_MOV + " WHERE " + COL_2 + " = '" + name + "'";

            myDB.execSQL(query);
        }



}

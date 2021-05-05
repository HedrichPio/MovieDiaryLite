package com.example.moviediarylite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.moviediarylite.AlertComponents.AlertDialogandToastMessages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddMovieActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText title_edittext_am;
    Spinner year_spinner_am, genre_spinner_am, rating_spinner_am, favourite_spinner_am;
    Button save_button_am;

    String title, genre, favourite;
    int year,rating;

    DatabaseHelper DB = new DatabaseHelper(this);

    AlertDialogandToastMessages alerts = new AlertDialogandToastMessages();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        title_edittext_am = findViewById(R.id.titleedittext_addmovie);
        year_spinner_am = findViewById(R.id.yearspinner_addmovie);
        genre_spinner_am = findViewById(R.id.genrespinner_addmovie);
        rating_spinner_am = findViewById(R.id.ratingspinner_addmovie);
        favourite_spinner_am = findViewById(R.id.favouritespinner_addmovie);

        save_button_am = findViewById(R.id.savebutton_addmovie);

        setSpinners();

    }


    public void saveMovie(View view){

        if(save_button_am.getText().equals("Save")){

            title = title_edittext_am.getText().toString();
            year = (int) year_spinner_am.getSelectedItem();
            genre = (String) genre_spinner_am.getSelectedItem();
            rating = (int) rating_spinner_am.getSelectedItem();
            favourite = (String) favourite_spinner_am.getSelectedItem();

            if(title.equals("")){
                alerts.showAlertDialog(this,"Title","Please provide a Title for the Movie.");
            }

            else{

                checkandAddMovie(title,year,genre,rating,favourite);

                save_button_am.setText("Reset");
            }
        }


        else if(save_button_am.getText().equals("Reset")){

            title_edittext_am.setText("");
            year_spinner_am.setSelection(0);
            genre_spinner_am.setSelection(0);
            rating_spinner_am.setSelection(0);
            favourite_spinner_am.setSelection(0);

            save_button_am.setText("Save");
        }

    }



    private void checkandAddMovie(String mtitle, int myear, String mgenre, int mrating, String mfavourite){


        Movie movie = new Movie(mtitle,myear,mgenre,mrating,mfavourite);

        ArrayList<Movie> movieListinDB = new ArrayList<>();

        movieListinDB = DB.getAllMovies();

        boolean found = false;

        for(Movie m : movieListinDB){

            if(m.getTitle().equalsIgnoreCase(movie.getTitle()) && m.getYear()==movie.getYear()){
                found=true;
            }
        }

        if(!found){

            StringBuffer buffer = new StringBuffer();
            buffer.append("Title : "+title + "\n");
            buffer.append("Year : "+year + "\n");
            buffer.append("Genre : "+genre + "\n");
            buffer.append("Rating : "+rating + "\n");
            buffer.append("Favorite : "+favourite );


            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Saved Movie Details as :");
            alert.setMessage(buffer.toString());
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    insertMovietoDB(movie);

                }
            });
            alert.setNegativeButton("Cancel", null);
            alert.show();


        }

        else{

            alerts.showAlertDialog(this, "Up to Date", "Movie already Exists");
        }
    }




    private void insertMovietoDB(Movie movie){

        boolean isInserted = DB.addMovietoDB(movie);

        if(isInserted){
            alerts.showToastMessage(this,"Movie Successfully Added");
        }
        else{
            alerts.showToastMessage(this, "Failed to add Movie");
        }
    }




    
    private void setSpinners(){

//  year spinner
        if (year_spinner_am != null) {
            year_spinner_am.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the int array and default spinner layout.
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getAllYears());

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (year_spinner_am != null) {
            year_spinner_am.setAdapter(adapter);
        }



//  genre spinner
        if (genre_spinner_am != null) {
            year_spinner_am.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the int array and default spinner layout.
        ArrayAdapter genre_adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getAllGenres());

        // Specify the layout to use when the list of choices appears.
        genre_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (genre_spinner_am != null) {
            genre_spinner_am.setAdapter(genre_adapter);
        }



//  rating spinner
        if (rating_spinner_am != null) {
            rating_spinner_am.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the int array and default spinner layout.
        ArrayAdapter adapterrating = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getRatingValues());

        // Specify the layout to use when the list of choices appears.
        adapterrating.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner.
        if (rating_spinner_am != null) {
            rating_spinner_am.setAdapter(adapterrating);
        }


//  favourite spinner
        if (favourite_spinner_am != null) {
            rating_spinner_am.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the int array and default spinner layout.
        ArrayAdapter adapterfav = new ArrayAdapter(this,android.R.layout.simple_spinner_item,getFavoriteValues());

        // Specify the layout to use when the list of choices appears.
        adapterfav.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Apply the adapter to the spinner.
        if (favourite_spinner_am != null) {
            favourite_spinner_am.setAdapter(adapterfav);
        }

    }



    private ArrayList<Integer> getAllYears(){

        ArrayList<Integer> yearsArray = new ArrayList<>();

        int year = Calendar.getInstance().get(Calendar.YEAR);

        for(int i = year; i>=1895; i--){
            yearsArray.add(i);
        }
        return yearsArray;
    }


    private ArrayList<String> getAllGenres(){


        ArrayList<String> genreArray = new ArrayList<>(Arrays.asList("Action","AI/IT","Animation","Cartel","Comedy","Family","Heist","Horror","Racing","Romance"));

        return genreArray;
    }


    //method returns an arraylist containing all the possible rating values from 0 to 10
    private ArrayList<Integer> getRatingValues(){

        ArrayList<Integer> ratingsArray = new ArrayList<>();

        for(int i = 0; i<=10; i++){
            ratingsArray.add(i);
        }
        return ratingsArray;
    }


    private ArrayList<String> getFavoriteValues(){

        ArrayList<String> favouriteArray = new ArrayList<>(Arrays.asList("No","Yes"));

        return favouriteArray;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        String spinnerLabel = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
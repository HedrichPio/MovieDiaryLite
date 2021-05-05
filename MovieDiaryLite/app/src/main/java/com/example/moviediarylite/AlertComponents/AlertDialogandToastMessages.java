package com.example.moviediarylite.AlertComponents;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;


public class AlertDialogandToastMessages {


    public void showToastMessage(Activity activity, String message){
        Toast.makeText(activity,message,Toast.LENGTH_SHORT).show();
    }


    public void showAlertDialog(Context context, String messageType, String message){

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(messageType);
        alert.setMessage(message);
        alert.setPositiveButton("OK",null);
        alert.show();
    }

    public void showAlertDialogforEmpty(Context context, String messageType, String message){



    }


}

package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class LoadingUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_user);
        String id = readFileContent();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                routeHandler(id);
            }
        }, 50);


    }

    public void routeHandler(String id){
        /*
            Checks with the string id, and sends the user to a login page if found to be "none",
            or their respective dashboard otherwise.
         */

        //Debug register


        if(id.equals("none")){
            sendToRegister();
            //sendToLogin();
        }
        //TO DO IMPLEMENT DASHBOARD ROUTING
    }

    public String readFileContent(){
        /*
        Checks the loginhistory.txt file on android device for a userid.
        if found returns the userid otherwise returns none
         */
        ContextWrapper c = new ContextWrapper(this);
        String filename= c.getFilesDir().getPath();
        Log.d("Test", filename);
        //Check if file exists
        File file = new File(filename);
        String id = "none";
        if(!file.exists()){
            Log.d("LoadingUserActivity", "File must be created");
            try{
                FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
                String text = "userid (Prototype File Not Finalized)\n" +
                        "-----------------------------------\n"+
                        "none";
                fos.write(text.getBytes());
                fos.close();
            }catch(Exception e) {
                Log.e("LoadingUserActivity", "Output Error");
            }
            return id;
        }
        else{
            Log.d("File Exists","FileExists");
            try{
                FileInputStream fis = openFileInput("loginhistory.txt");
                BufferedReader b = new BufferedReader(new InputStreamReader(fis));
                //Readout header and divider
                b.readLine();
                b.readLine();
                id = b.readLine();
                fis.close();
            }catch(Exception e){
                Log.e("LoadingUserActivity", e.getLocalizedMessage());
            }
        }
        return id;
    }

    public void sendToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void sendToRegister(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
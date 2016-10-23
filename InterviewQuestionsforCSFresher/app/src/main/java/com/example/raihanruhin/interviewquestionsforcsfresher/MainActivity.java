package com.example.raihanruhin.interviewquestionsforcsfresher;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

     //Toolbar toolbar;


     RecyclerView recyclerView;
     MyCustomAdapter adapter;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         //toolbar = (Toolbar) findViewById(R.id.toolbar);
         //setSupportActionBar(toolbar);
         if(!isNetworkStatusAvialable(getApplicationContext())) {
             Toast.makeText(getApplicationContext(), "Error!!!\nYou are corrently not coneected to Internet.", Toast.LENGTH_LONG).show();
             Toast.makeText(getApplicationContext(), "This app require Internet Connection to load question and answer data from server", Toast.LENGTH_LONG).show();
         }

         //toolbar.setTitle("Interview Questions");
         //toolbar.setSubtitle("For CS Freshers");

         recyclerView = (RecyclerView) findViewById(R.id.recycleView);
         adapter = new MyCustomAdapter(this,Data.getData());
         recyclerView.setAdapter(adapter);
         recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
     }

    public static boolean isNetworkStatusAvialable (Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null)
        {
            NetworkInfo netInfos = connectivityManager.getActiveNetworkInfo();
            if(netInfos != null)
                if(netInfos.isConnected())
                    return true;
        }
        return false;
    }
 }


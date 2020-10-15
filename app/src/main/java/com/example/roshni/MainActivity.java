package com.example.roshni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                try{

                    Intent intent = new Intent(MainActivity.this,SelectMenu.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        },3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        //Remove all the callbacks otherwise navigation will execute even after activity is killed or closed.
        handler.removeCallbacksAndMessages(null);
    }
}
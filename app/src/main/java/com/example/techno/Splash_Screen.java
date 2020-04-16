package com.example.techno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        Thread thread= new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                }catch (Exception e)
                {


                }finally {
                    Intent intent=new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}

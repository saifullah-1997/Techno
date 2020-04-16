package com.example.techno;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.techno.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void moveToSignInPage(View view)
    {
        try
        {
            startActivity(new Intent(this,Login.class));
        }
        catch (Exception e)
        {
            Toast.makeText(this, "moveToSignInPage:"
                    +e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

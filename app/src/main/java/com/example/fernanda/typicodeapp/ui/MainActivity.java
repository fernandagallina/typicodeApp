package com.example.fernanda.typicodeapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fernanda.typicodeapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_main, new MainFragment())
                    .commit();
        }
    }
}

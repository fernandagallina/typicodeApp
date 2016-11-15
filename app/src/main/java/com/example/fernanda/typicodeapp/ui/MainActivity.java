package com.example.fernanda.typicodeapp.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fernanda.typicodeapp.R;
import com.example.fernanda.typicodeapp.model.User;

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

    public void replaceFragments(User user) {

        if(!isFinishing()) {

            UserFragment fragment = new UserFragment();
            fragment.setUser(user);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_main, fragment, "MAIN_FRAGMENT")
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}

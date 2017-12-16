package com.example.cowboy.authmodule.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cowboy.authmodule.auth.AuthActivity;
import com.example.cowboy.authmodule.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
    }
}

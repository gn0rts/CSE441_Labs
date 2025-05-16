package com.example.lab02;

import static com.example.lab02.R.layout.activity_subactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

class subactivity extends AppCompatActivity {
    Button btnok;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(activity_subactivity);
        btnok = findViewById(R.id.btnok);
        btnok.setOnClickListener(view -> {

        });
    }
}

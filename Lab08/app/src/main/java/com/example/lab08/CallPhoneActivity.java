package com.example.lab08;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CallPhoneActivity extends AppCompatActivity {

    EditText edtcall;
    ImageButton btncallphone;
    Button btnback1;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_layout);

        edtcall = findViewById(R.id.edtcall);
        btnback1 = findViewById(R.id.btnback1);
        btncallphone = findViewById(R.id.btncallphone);

        btncallphone.setOnClickListener(view -> {
            Intent callintent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:" + edtcall.getText().toString()));

            if (ActivityCompat.checkSelfPermission(CallPhoneActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CallPhoneActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
                return;
            }

            startActivity(callintent);
        });

        btnback1.setOnClickListener(view -> finish());
    }
}

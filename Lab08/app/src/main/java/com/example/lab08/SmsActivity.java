package com.example.lab08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SmsActivity extends AppCompatActivity {

    EditText edtsms;
    Button btnback2;
    ImageButton btnsendsms;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);

        edtsms = findViewById(R.id.edtsms);
        btnback2 = findViewById(R.id.btnback2);
        btnsendsms = findViewById(R.id.btnsendsms);

        btnsendsms.setOnClickListener(view -> {
            String phoneNumber = edtsms.getText().toString();
            if (!phoneNumber.isEmpty()) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + phoneNumber));
                startActivity(smsIntent);
            }
        });

        // ✅ Lambda cho nút quay lại
        btnback2.setOnClickListener(view -> finish());
    }
}

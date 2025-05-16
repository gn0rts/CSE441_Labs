package com.example.lab01;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB, edtKQ;
    Button btnC;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtKQ);
        btnC = findViewById(R.id.btntong);

        btnC.setOnClickListener(view -> {
            try {
                String strA = edtA.getText().toString().trim();
                String strB = edtB.getText().toString().trim();

                if (strA.isEmpty() || strB.isEmpty()) {
                    Toast.makeText(this, "Vui lòng nhập cả hai số", Toast.LENGTH_SHORT).show();
                    return;
                }

                int a = Integer.parseInt(strA);
                int b = Integer.parseInt(strB);
                int c = a + b;

                edtKQ.setText(String.valueOf(c));
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Vui lòng nhập số hợp lệ (có thể âm hoặc dương)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
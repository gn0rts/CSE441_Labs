package com.example.playermanager1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPlayerActivity extends AppCompatActivity {

    private EditText etName, etAge, etPosition;
    private DatabaseReference playersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        // Liên kết giao diện
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etPosition = findViewById(R.id.etPosition);
        Button btnSave = findViewById(R.id.btnSave);

        // Kết nối đến node "players"
        playersRef = FirebaseDatabase.getInstance().getReference("players");

        btnSave.setOnClickListener(view -> {
            String name = etName.getText().toString().trim();
            String ageStr = etAge.getText().toString().trim();
            String position = etPosition.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(ageStr) || TextUtils.isEmpty(position)) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            int age = Integer.parseInt(ageStr);
            generateNewIdAndSave(name, age, position);
        });
    }

    private void generateNewIdAndSave(String name, int age, String position) {
        playersRef.orderByKey().limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int newNumber = 1;

                if (snapshot.exists()) {
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String lastId = child.getKey(); // MBR005
                        assert lastId != null;
                        newNumber = Integer.parseInt(lastId.substring(3)) + 1;
                    }
                }

                @SuppressLint("DefaultLocale") String newId = String.format("MBR%03d", newNumber);

                Player newPlayer = new Player(newId, name, age, position);
                playersRef.child(newId).setValue(newPlayer).addOnSuccessListener(unused -> {
                    Toast.makeText(AddPlayerActivity.this, "Đã thêm hội viên", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(AddPlayerActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(AddPlayerActivity.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

    package com.example.b1_appchattcp;

    import androidx.appcompat.app.AppCompatActivity;

    import android.annotation.SuppressLint;
    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.PrintWriter;
    import java.net.Socket;

    /** @noinspection ALL*/
    public class MainActivity extends AppCompatActivity {

        private EditText edtMessage;
        private TextView txtChat;

        private Socket socket;
        private PrintWriter out;

        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            edtMessage = findViewById(R.id.edtMessage);
            Button btnSend = findViewById(R.id.btnSend);
            txtChat = findViewById(R.id.txtChat);

            btnSend.setOnClickListener(this::sendMessage);

            new ConnectTask().execute();
        }

        private class ConnectTask extends AsyncTask<Void, String, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    // Thay đổi địa chỉ IP nếu cần
                    socket = new Socket("192.168.1.100", 5555);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String message;
                    while ((message = in.readLine()) != null) {
                        publishProgress(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                txtChat.append("\nServer: " + values[0]);
            }
        }

        public void sendMessage(View view) {
            String message = edtMessage.getText().toString().trim();
            if (!message.isEmpty() && out != null) {
                out.println(message);
                txtChat.append("\nBạn: " + message);
                edtMessage.setText("");
            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

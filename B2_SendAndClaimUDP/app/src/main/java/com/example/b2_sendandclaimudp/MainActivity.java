package com.example.b2_sendandclaimudp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    EditText edtMessage;
    Button btnSend;
    TextView txtResponse;

    final int SERVER_PORT = 9876;
    final String SERVER_IP = "192.168.1.100"; // hoặc địa chỉ broadcast: "255.255.255.255"

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMessage = findViewById(R.id.edtMessage);
        btnSend = findViewById(R.id.btnSend);
        txtResponse = findViewById(R.id.txtResponse);

        btnSend.setOnClickListener(v -> sendUDPMessage(edtMessage.getText().toString()));
    }

    @SuppressLint("SetTextI18n")
    private void sendUDPMessage(String message) {
        new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.setBroadcast(true); // Cho phép broadcast

                InetAddress serverAddr = InetAddress.getByName(SERVER_IP);
                byte[] sendData = message.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddr, SERVER_PORT);
                socket.send(sendPacket);

                // Nhận phản hồi
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.setSoTimeout(5000); // timeout nếu không có phản hồi

                socket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

                runOnUiThread(() -> txtResponse.setText("Phản hồi: " + response));
                socket.close();
            } catch (Exception e) {
                runOnUiThread(() -> txtResponse.setText("Lỗi: " + e.getMessage()));
            }
        }).start();
    }
}

package com.example.forum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("FCM Log", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get and print the current registration token
                        String token = task.getResult();
                        Log.d("FCM Log", "Current token: " + token);

                        // Send the token to the backend
                        sendTokenToServer(token);
                    }
                });
    }

    private void sendTokenToServer(String token) {
        new Thread(() -> {
            try {
                URL url = new URL("localhost:3500");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setDoOutput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put("userId", 16940821101711020L);
                jsonParam.put("token", token);

                DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream());
                out.writeBytes(jsonParam.toString());
                out.flush();
                out.close();

                int responseCode = httpURLConnection.getResponseCode();
                Log.d("SERVER", "Server response code:" + responseCode);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
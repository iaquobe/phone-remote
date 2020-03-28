package com.example.phone_remote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConnectorInterface connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.startButton);
        Button optionButton = findViewById(R.id.optionButton);
        Button connectButton = findViewById(R.id.connectButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startButIntent = new Intent(getApplicationContext(), RemoteActivity.class);
                startActivity(startButIntent);
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startButIntent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(startButIntent);
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (connector == null) {
                    Log.d("", "Creating new Connector");
                    connector = new Connector(((CustomApplication)getApplicationContext()).getIp());
                    Log.d("", "Opening connection");
                    connector.openConnection();
                }
            }
        });
    }
}

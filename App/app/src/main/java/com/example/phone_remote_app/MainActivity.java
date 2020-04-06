package com.example.phone_remote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private ConnectorInterface connector;

    Button startButton;
    Button optionButton;
    Button connectButton;
    MainActivity main = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CustomApplication)getApplicationContext()).setConnector(connector);

        startButton = findViewById(R.id.startButton);
        optionButton = findViewById(R.id.optionButton);
        connectButton = findViewById(R.id.connectButton);

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

        connectButton.setOnClickListener(connectClick);

    }

    private void updateConnectBut(){
        //TODO CHANGE LISTENER!!!
        if(connector == null || !connector.isConnected()){
            connectButton.setText("Connect");
            return;
        }
        connectButton.setText("Disconnect");
    }

    private View.OnClickListener connectClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (connector == null) {
                Log.d("", "Creating new Connector");
                connector = new Connector(((CustomApplication)getApplicationContext()).getIp(), main);
                Log.d("", "Opening connection");
                connector.openConnection();
                ((CustomApplication) getApplicationContext()).setConnector(connector);
            } else {
                Log.d("", "Connector already active");
            }
            updateConnectBut();
        }
    };

    private View.OnClickListener disconnectClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (connector == null) {
                Log.d("", "No Connector to disconnect");
                return;
            } else {
                removeConnector();
            }
            updateConnectBut();
        }
    };

    public void removeConnector() {
        Log.d("","Removing connector");
        if (connector != null){
            connector.closeConnection();
            connector = null;
            ((CustomApplication) getApplicationContext()).setConnector(null);
        }
    }

}

package com.example.phone_remote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class RemoteActivity extends AppCompatActivity {

    private ConnectorInterface connector;
    private RemoteActivity remote;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        String location = "m " + x + " " + y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "touched down");
                if((connector != null) && connector.isConnected()) {
                    connector.sendData("a d");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG", location);
                if((connector != null) && connector.isConnected()) {
                    connector.sendData(location);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "touched up");
                if((connector != null) && connector.isConnected()) {
                    connector.sendData("a u");
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        remote = this;
        connector = ((CustomApplication)getApplicationContext()).getConnector();
    }
}

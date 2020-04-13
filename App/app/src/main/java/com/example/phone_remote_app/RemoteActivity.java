package com.example.phone_remote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RemoteActivity extends AppCompatActivity {

    private ConnectorInterface connector;
    private int startX;
    private int startY;
    private int clickThreshold = 250;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG", "touched down");
                startX = x;
                startY = y;
                if((connector != null) && connector.isConnected()) {
                    connector.sendData("a d");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                x -= startX;
                y -= startY;
                String location = "m " + x + " " + y;
                Log.i("TAG", location);
                if((connector != null) && connector.isConnected()) {
                    connector.sendData(location);
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG", "touched up");
                startX = 0;
                startY = 0;
                long duration = event.getEventTime() - event.getDownTime();
                if((connector != null) && connector.isConnected()) {
                    connector.sendData("a u");
                }
                if (duration < clickThreshold) {
                    Log.i("", "a c");
                    if((connector != null) && connector.isConnected()) {
                        connector.sendData("a c");
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote);

        connector = ((CustomApplication)getApplicationContext()).getConnector();
    }
}

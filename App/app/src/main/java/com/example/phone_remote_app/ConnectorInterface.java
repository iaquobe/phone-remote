package com.example.phone_remote_app;

import android.os.AsyncTask;

public interface ConnectorInterface {

    AsyncTask openConnection();

    AsyncTask sendData(String data);

    AsyncTask closeConnection();

    boolean isConnected();
}

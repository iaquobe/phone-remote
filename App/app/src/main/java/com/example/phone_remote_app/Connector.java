package com.example.phone_remote_app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connector implements ConnectorInterface {

    private boolean connected = false;
    private final int port = 9000;
    private Socket client;
    private String ip;
    private DataOutputStream out;
    private MainActivity main;

    public boolean isConnected() {
        return connected;
    }

    public Connector(String ip, MainActivity main) {
        this.ip = ip;
        this.main = main;
        Log.d("", "Connector created");
    }

    @Override
    public OpenConnectionTask openConnection() {
        OpenConnectionTask task = new OpenConnectionTask();
        task.execute();
        return task;
    }

    @Override
    public SendDataTask sendData(String data) {
        SendDataTask task = new SendDataTask();
        task.execute(data);
        return task;
    }

    @Override
    public CloseConnectionTask closeConnection() {
        CloseConnectionTask task = new CloseConnectionTask();
        task.execute();
        return task;
    }

    private boolean openConnectionOp() {
        boolean success = true;
        Log.d("", "Trying to connect to: " + ip);
        if (ip == null || !validIP(ip)) success = false;

        try {
            this.client = new Socket(ip, port);
            out = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            Log.d("", e.toString());
            success = false;
        }
        if(!success){
            Log.d("","Connecting not successful");
            return false;
        }

        connected = true;
        return true;
    }

    private boolean sendDataOp(String data) {
        try {
            out.writeChars(data);
            out.flush();
        } catch (Exception e) {
            Log.d("", e.toString());
            return false;
        }
        return true;
    }

    private boolean closeConnectionOp() {
        if (!connected) {
            return false;
        }
        try {
            out.close();
            client.close();
            connected = false;
        } catch (IOException e) {
            Log.d("", e.toString());
            return false;
        }
        return true;
    }

    private boolean validIP(String ip) {
        char[] ipArray = ip.toCharArray();
        for (char c : ipArray) {
            if (((c < '0') || (c > '9')) && c != '.') {
                return false;
            }
        }
        return true;
    }

    private class OpenConnectionTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... Voids) {
            return openConnectionOp();
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (!success){
                main.removeConnector();
            }
        }
    }

    private class CloseConnectionTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... Voids) {
            return closeConnectionOp();
        }
    }

    private class SendDataTask extends AsyncTask<String,Void,Boolean>{
        @Override
        protected Boolean doInBackground(String... strings) {
            return sendDataOp(strings[0]);
        }
    }
}

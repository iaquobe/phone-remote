package com.example.phone_remote_app;

import android.app.Application;
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

    public boolean isConnected() {
        return connected;
    }

    public Connector(String ip) {
        this.ip = ip;
        Log.d("", "Connector created");
    }

    @Override
    public boolean openConnection() {
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

    @Override
    public boolean sendData(String data) {
        try {
            out.writeChars(data);
            out.flush();
        } catch (Exception e) {
            Log.d("", e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean closeConnection() {
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
}

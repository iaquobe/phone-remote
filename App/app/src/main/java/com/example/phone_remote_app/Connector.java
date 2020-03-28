package com.example.phone_remote_app;

import android.app.Application;
import android.util.Log;
import java.io.DataOutputStream;
import java.net.Socket;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Connector implements ConnectorInterface {

    public boolean connected = false;

    private final int port = 9000;
    private Socket client;
    private String ip;
    private DataOutputStream out;
    private Application applicaton;

    public Connector(String ip) {
        this.ip = ip;
        Log.d("", "Connector created");
    }

    @Override
    public boolean openConnection() {
        Log.d("","Trying to connect to: " + ip);
        if (ip == null || !validIP(ip)) return false;

        try {
            this.client = new Socket(ip, port);
        } catch (Exception e) {
            Log.d("", e.toString());
            return false;
        }

        try {
            out = new DataOutputStream(client.getOutputStream());
        } catch (Exception e) {
            Log.d("", e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean sendData(String data) {
        try {
            out.writeUTF(data);
            out.flush();
        } catch (Exception e) {
            Log.d("", e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean closeConnection() {
        if (!connected){
            return false;
        }
        return false;
    }

    private boolean validIP(String ip){
        char[] ipArray = ip.toCharArray();
        for (char c : ipArray) {
            if (((c < '0') || (c > '9')) && c != '.'){
                return false;
            }
        }
        return true;
    }
}

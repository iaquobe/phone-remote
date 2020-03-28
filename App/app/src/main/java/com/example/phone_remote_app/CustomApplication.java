package com.example.phone_remote_app;

import android.app.Application;

public class CustomApplication extends Application {
    private String ip = null;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

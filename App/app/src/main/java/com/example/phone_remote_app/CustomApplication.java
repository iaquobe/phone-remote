package com.example.phone_remote_app;

import android.app.Application;

public class CustomApplication extends Application {
    private String ip = null;
    private ConnectorInterface connector = null;

    public ConnectorInterface getConnector() {
        return connector;
    }

    public void setConnector(ConnectorInterface connector) {
        this.connector = connector;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

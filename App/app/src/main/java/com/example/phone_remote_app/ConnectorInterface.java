package com.example.phone_remote_app;

public interface ConnectorInterface {

    boolean openConnection();

    boolean sendData(String data);

    boolean closeConnection();

    boolean isConnected();
}

package com.example.phone_remote_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class OptionsActivity extends AppCompatActivity {

    EditText ipEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ipEditText = (EditText) findViewById(R.id.ipEditText);
        String savedIP = ((CustomApplication) getApplicationContext()).getIp();
        if (savedIP != null) {
            ipEditText.setText(savedIP);
        }

    }

    @Override
    public void onBackPressed(){
        String input = ipEditText.getText().toString().trim();
        ((CustomApplication) getApplicationContext()).setIp(input);
        Log.d("", input + " saved");
        finish();
        return;
    }

}

package com.example.hvac_monitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button button_hist, button_live, button_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MainActivity.this, "Firebase Connection Successful", Toast.LENGTH_LONG).show();

        // Home screen buttons: history and live data
        button_hist = (Button) findViewById(R.id.hist_button);
        button_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openHistActivity();
            }
        });

        button_live = (Button) findViewById(R.id.live_button);
        button_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openLiveActivity();
            }
        });

        button_contact = (Button) findViewById(R.id.button_contact);
        button_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openContactActivity();
            }
        });
    }

    //opening activity functions
    public void openHistActivity(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void openLiveActivity(){
        Intent intent = new Intent(this, LiveActivity.class);
        startActivity(intent);
    }

    public void openContactActivity(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

}

package com.example.hvac_monitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LiveActivity extends AppCompatActivity {

    TextView current1, current2, current3, current4, current5, current6, current7, current8;
    TextView temp1, temp2, temp3, temp4;
    TextView pressure1, pressure2;
    TextView air1;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_live);

        //temperature
        temp1 =  (TextView)findViewById(R.id.temperature_show1);

        //current
        current1 =  (TextView)findViewById(R.id.current_show1);

        //pressure
        pressure1 =  (TextView)findViewById(R.id.pressure_show1);
        pressure2 =  (TextView)findViewById(R.id.pressure_show2);

        //air
        air1 =  (TextView)findViewById(R.id.airflow_show1);

        //read data button
        btn = (Button)findViewById(R.id.readings_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reff = FirebaseDatabase.getInstance().getReference().child("sensors");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Obtaining and displaying readings from TEMPERATURE sensors
                        String tempSensor1 = dataSnapshot.child("temperature").child("temp1").getValue().toString();
                        temp1.setText(tempSensor1);

                        //Obtaining and displaying readings from CURRENT sensors
                        String currSensor1 = dataSnapshot.child("current").child("curr1").getValue().toString();
                        current1.setText(currSensor1);

                        //Obtaining and displaying readings from PRESSURE sensors
                        String pressureSensor1 = dataSnapshot.child("pressure").child("press1").getValue().toString();
                        String pressureSensor2 = dataSnapshot.child("pressure").child("press2").getValue().toString();
                        pressure1.setText(pressureSensor1);
                        pressure2.setText(pressureSensor2);

                        //Obtaining and displaying readings from AIRFLOW sensor
                        String airSensor1 = dataSnapshot.child("airflow").child("air1").getValue().toString();
                        air1.setText(airSensor1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                if(item.getItemId() == R.id.nav_home){
                    openMainActivity();
                    Toast.makeText(LiveActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    drawerLayout.closeDrawer(GravityCompat.START);

                }
                if(item.getItemId() == R.id.nav_history){
                    openHistActivity();
                    Toast.makeText(getApplicationContext(), "History Log", Toast.LENGTH_SHORT).show();
                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId() == R.id.nav_live){
                    Toast.makeText(LiveActivity.this, "Live-Data", Toast.LENGTH_SHORT).show();
                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId() == R.id.nav_contacts){
                    openContactActivity();
                    Toast.makeText(getApplicationContext(), "Contacts", Toast.LENGTH_SHORT).show();
                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                if(item.getItemId() == R.id.nav_saved_contacts){
                    openDisplayContactActivity();
                    Toast.makeText(getApplicationContext(), "Saved Contacts", Toast.LENGTH_SHORT).show();
                    DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openHistActivity(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void openContactActivity(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
    public void openDisplayContactActivity(){
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
    }

}

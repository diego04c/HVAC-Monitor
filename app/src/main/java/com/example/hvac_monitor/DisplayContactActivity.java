package com.example.hvac_monitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisplayContactActivity extends AppCompatActivity {


    TextView name1, name2, name3, name4;
    TextView last1, last2, last3, last4;
    TextView phone1, phone2, phone3, phone4;
    TextView email1, email2, email3, email4;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_displaycontact);

        //tech1
        name1 = (TextView)findViewById(R.id.firstName);
        last1 = (TextView)findViewById(R.id.lastName);
        email1 = (TextView)findViewById(R.id.email_show);
        phone1 = (TextView)findViewById(R.id.phone_show);

        //tech2
        name2 = (TextView)findViewById(R.id.firstName2);
        last2 = (TextView)findViewById(R.id.lastName2);
        email2 = (TextView)findViewById(R.id.email_show2);
        phone2 = (TextView)findViewById(R.id.phone_show2);

        //tech3
        name3 = (TextView)findViewById(R.id.firstName3);
        last3 = (TextView)findViewById(R.id.lastName3);
        email3 = (TextView)findViewById(R.id.email_show3);
        phone3 = (TextView)findViewById(R.id.phone_show3);

        //tech4
        name4 = (TextView)findViewById(R.id.firstName4);
        last4 = (TextView)findViewById(R.id.lastName4);
        email4 = (TextView)findViewById(R.id.email_show4);
        phone4 = (TextView)findViewById(R.id.phone_show4);

        btn = (Button)findViewById(R.id.getcontacts_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reff = FirebaseDatabase.getInstance().getReference().child("contacts");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String email11 = dataSnapshot.child("technician2").child("address").getValue().toString();
                        String last11 = dataSnapshot.child("technician2").child("lastName").getValue().toString();
                        String name11 = dataSnapshot.child("technician2").child("name").getValue().toString();
                        String phone11 = dataSnapshot.child("technician2").child("phone").getValue().toString();
                        name1.setText(name11);
                        last1.setText(last11);
                        email1.setText(email11);
                        phone1.setText(phone11);

                        String email12 = dataSnapshot.child("technician3").child("address").getValue().toString();
                        String last12 = dataSnapshot.child("technician3").child("lastName").getValue().toString();
                        String name12 = dataSnapshot.child("technician3").child("name").getValue().toString();
                        String phone12 = dataSnapshot.child("technician3").child("phone").getValue().toString();
                        name2.setText(name12);
                        last2.setText(last12);
                        email2.setText(email12);
                        phone2.setText(phone12);

                        String email13 = dataSnapshot.child("technician4").child("address").getValue().toString();
                        String last13 = dataSnapshot.child("technician4").child("lastName").getValue().toString();
                        String name13 = dataSnapshot.child("technician4").child("name").getValue().toString();
                        String phone13 = dataSnapshot.child("technician4").child("phone").getValue().toString();
                        name3.setText(name13);
                        last3.setText(last13);
                        email3.setText(email13);
                        phone3.setText(phone13);

                        String email14 = dataSnapshot.child("technician5").child("address").getValue().toString();
                        String last14 = dataSnapshot.child("technician5").child("lastName").getValue().toString();
                        String name14 = dataSnapshot.child("technician5").child("name").getValue().toString();
                        String phone14 = dataSnapshot.child("technician5").child("phone").getValue().toString();
                        name4.setText(name14);
                        last4.setText(last14);
                        email4.setText(email14);
                        phone4.setText(phone14);

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
                    Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
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
                    openLiveActivity();
                    Toast.makeText(getApplicationContext(), "Live-Data", Toast.LENGTH_SHORT).show();
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

    public void openLiveActivity(){
        Intent intent = new Intent(this, LiveActivity.class);
        startActivity(intent);
    }

    public void openContactActivity(){
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }
}

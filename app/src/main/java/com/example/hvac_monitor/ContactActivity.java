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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContactActivity extends AppCompatActivity {

    EditText name, lastname, address, phone;
    Button btnsave;
    Button updateBtn;
    FirebaseDatabase database;
    DatabaseReference reff;
    Contacts contact;


    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_contact);

        name = (EditText)findViewById(R.id.tech_first_name_show);
        lastname = (EditText)findViewById(R.id.tech_show_last_name);
        address = (EditText)findViewById(R.id.tech_address_show);
        phone = (EditText)findViewById(R.id.num_show);
        btnsave = (Button)findViewById(R.id.save_button);
        contact = new Contacts();
        database = FirebaseDatabase.getInstance();
        reff = database.getReference().child("contacts");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                contact.setName(name.getText().toString().trim());
                contact.setLastName(lastname.getText().toString().trim());
                contact.setAddress(address.getText().toString().trim());
                contact.setPhone(phone.getText().toString().trim());
                reff.child("technician"+String.valueOf(maxid+1)).setValue(contact);
                Toast.makeText(ContactActivity.this, "Contact Saved.", Toast.LENGTH_LONG).show();

            }
        });

        updateBtn = (Button) findViewById(R.id.show_contact);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openDisplayContactActivity();
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

    //opening activity function
    public void openDisplayContactActivity(){
        Intent intent = new Intent(this, DisplayContactActivity.class);
        startActivity(intent);
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


}
